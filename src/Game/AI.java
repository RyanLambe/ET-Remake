package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Physics.Collider;
import Game.UI.PauseMenu;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class AI extends SpriteEntity {

    enum State {
        Patrol,
        Chase,
        GoToJail,
    }
    State state = State.Patrol;

    // sprites
    Sprite FBIleft = AssetManager.LoadAnimation("FBI/Left/0001.png","FBI/Left/0002.png","FBI/Left/0003.png","FBI/Left/0004.png","FBI/Left/0005.png","FBI/Left/0006.png","FBI/Left/0007.png","FBI/Left/0008.png","FBI/Left/0009.png","FBI/Left/0010.png","FBI/Left/0011.png");
    Sprite FBIright = AssetManager.LoadAnimation("FBI/Right/0001.png","FBI/Right/0002.png","FBI/Right/0003.png","FBI/Right/0004.png","FBI/Right/0005.png","FBI/Right/0006.png","FBI/Right/0007.png","FBI/Right/0008.png","FBI/Right/0009.png","FBI/Right/0010.png","FBI/Right/0011.png");
    Sprite SciLeft = AssetManager.LoadAnimation("Scientist/Left/0001.png","Scientist/Left/0002.png","Scientist/Left/0003.png","Scientist/Left/0004.png","Scientist/Left/0005.png","Scientist/Left/0006.png","Scientist/Left/0007.png","Scientist/Left/0008.png","Scientist/Left/0009.png","Scientist/Left/0010.png");
    Sprite SciRight = AssetManager.LoadAnimation("Scientist/Right/0001.png","Scientist/Right/0002.png","Scientist/Right/0003.png","Scientist/Right/0004.png","Scientist/Right/0005.png","Scientist/Right/0006.png","Scientist/Right/0007.png","Scientist/Right/0008.png","Scientist/Right/0009.png","Scientist/Right/0010.png");

    // go to Jail vars
    public static boolean isHoldingPlayer = false;

    // chase vars
    String chaseScreen = "";
    float speed = 20;

    // patrol vars
    private Clock positionClock;

    float screenWidth = 160;

    float duration;
    float verticalPhases;
    float distance = screenWidth * 4;
    float verticalDistance = 20.0f;

    boolean FBI;
    boolean invertDirection;
    boolean reesePunishment;

    float lastXPos = 0;

    public AI(boolean FBISprite, boolean invertDirection, float sinPhases, float cycleDuration, boolean reesePunishment){
        FBI = FBISprite;
        this.invertDirection = invertDirection;
        verticalPhases = sinPhases;
        duration = cycleDuration;
        this.reesePunishment = reesePunishment;
    }

    @Override
    public void Start() {
        positionClock = new Clock();
        transform.Scale(2);

        FBIleft.fps = 8;
        FBIright.fps = 8;
        SciLeft.fps = 8;
        SciRight.fps = 8;

        collider.enabled = true;
        collider.SetRadius(400);
    }

    @Override
    public void Update() {

        if(PauseMenu.isPaused){
            positionClock.Pause();
            return;
        }
        else{
            positionClock.Resume();
        }

        float MinusOneToOne = ((positionClock.GetTime() % duration) / duration) * 2 - 1;

        if (state == State.Patrol) {

            String zone = Game.zoneManager.getCurrentZone().name;
            float offset;
            if(zone.equals("LeftEdge")) {
                offset = -screenWidth / 2;
            }
            else if(zone.equals("CenterLeft")) {
                offset = (-screenWidth / 2) - screenWidth * 1;
            }
            else if(zone.equals("CenterRight")) {
                offset = (-screenWidth / 2) - screenWidth * 2;
            }
            else if(zone.equals("RightEdge")) {
                offset = (-screenWidth / 2) - screenWidth * 3;
            }
            else {
                transform.position.x = 2000;
                return;
            }

            float unscaledX = Math.abs(MinusOneToOne);
            if(invertDirection)
                unscaledX = 1 - unscaledX;

            transform.position.x = (distance * unscaledX) + offset;
            transform.position.y = verticalDistance * (float)Math.sin((MinusOneToOne) * Math.PI * verticalPhases);

        }

        if(state == State.Chase) {

            if(!Game.zoneManager.getCurrentZone().name.equals(chaseScreen)) {
                state = State.Patrol;
                transform.position.x = 2000;
                return;
            }

            if(isHoldingPlayer) {
                state = State.Patrol;
            }

            if(Vector2f.distance(transform.position.x, transform.position.y, Game.getPlayer().transform.position.x, Game.getPlayer().transform.position.y) < 10){
                isHoldingPlayer = true;
                state = State.GoToJail;
            }

            MoveTowards(Game.getPlayer().transform.position);
        }

        if(state == State.GoToJail) {

            if(!Game.zoneManager.getCurrentZone().name.equals("Whitehouse")) {
                transform.position.y -= speed * Clock.DeltaTime();
            }
            else {

                if(transform.position.y < 0)
                    transform.position.y = Math.min(-transform.position.y, 40);

                if (Vector2f.distance(transform.position.x, transform.position.y, -40, 5) > 10) {
                    MoveTowards(new Vector3f(-40, 5, 0));
                }
                else {
                    state = State.Patrol;
                    isHoldingPlayer = false;
                    if(reesePunishment){
                        GameState.reeseCount -= 1;
                    }
                    else {
                        GameState.stamina -= 0.1f;
                    }
                }
            }

            Game.getPlayer().transform.position = new Vector3f(transform.position);
        }

        // set sprite
        if(transform.position.x < lastXPos) {
            if(FBI)
                GetSpriteRenderer().sprite = FBIleft;
            else
                GetSpriteRenderer().sprite = SciLeft;
        }
        else {
            if(FBI)
                GetSpriteRenderer().sprite = FBIright;
            else
                GetSpriteRenderer().sprite = SciRight;
        }
        lastXPos = transform.position.x;
    }

    private void MoveTowards(Vector3f pos) {
        Vector3f dir = new Vector3f(pos);
        dir.x -= transform.position.x;
        dir.y -= transform.position.y;
        dir.z = 0;
        dir.normalize();
        dir.mul(speed * Clock.DeltaTime());
        transform.position.add(dir);
    }

    @Override
    public void OnCollision(Entity other) {
        if(isHoldingPlayer || Game.zoneManager.getCurrentZone().name.equals("Whitehouse") || PauseMenu.isPaused)
            return;
        if(other.tag.equals("Player")) {
            state = State.Chase;
            chaseScreen = Game.zoneManager.getCurrentZone().name;
        }
    }
}
