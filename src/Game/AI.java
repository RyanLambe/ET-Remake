package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;

public class AI extends SpriteEntity {

    enum State {
        Patrol,
        Chase,
    }
    State state = State.Patrol;

    // sprites
    Sprite FBIleft = AssetManager.LoadAnimation("FBI/Left/0001.png","FBI/Left/0002.png","FBI/Left/0003.png","FBI/Left/0004.png","FBI/Left/0005.png","FBI/Left/0006.png","FBI/Left/0007.png","FBI/Left/0008.png","FBI/Left/0009.png","FBI/Left/0010.png","FBI/Left/0011.png");
    Sprite FBIright = AssetManager.LoadAnimation("FBI/Right/0001.png","FBI/Right/0002.png","FBI/Right/0003.png","FBI/Right/0004.png","FBI/Right/0005.png","FBI/Right/0006.png","FBI/Right/0007.png","FBI/Right/0008.png","FBI/Right/0009.png","FBI/Right/0010.png","FBI/Right/0011.png");
    Sprite SciLeft = AssetManager.LoadAnimation("Scientist/Left/0001.png","Scientist/Left/0002.png","Scientist/Left/0003.png","Scientist/Left/0004.png","Scientist/Left/0005.png","Scientist/Left/0006.png","Scientist/Left/0007.png","Scientist/Left/0008.png","Scientist/Left/0009.png","Scientist/Left/0010.png");
    Sprite SciRight = AssetManager.LoadAnimation("Scientist/Right/0001.png","Scientist/Right/0002.png","Scientist/Right/0003.png","Scientist/Right/0004.png","Scientist/Right/0005.png","Scientist/Right/0006.png","Scientist/Right/0007.png","Scientist/Right/0008.png","FBI/Right/0009.png","Scientist/Right/0010.png");


    // patrol vars
    private Clock positionClock;

    float screenWidth = 160;

    float duration;
    float verticalPhases;
    float distance = screenWidth * 4;
    float verticalDistance = 20.0f;

    boolean FBI;
    boolean invertDirection;

    public AI(boolean FBISprite, boolean invertDirection, float sinPhases, float cycleDuration){
        FBI = FBISprite;
        this.invertDirection = invertDirection;
        verticalPhases = sinPhases;
        duration = cycleDuration;
    }

    @Override
    public void Start() {
        positionClock = new Clock();
        transform.Scale(2);
        FBIleft.fps = 8;
        FBIright.fps = 8;
        SciLeft.fps = 8;
        SciRight.fps = 8;
    }

    @Override
    public void Update() {

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

        // set sprite
        if(MinusOneToOne < 0) {
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
    }

    @Override
    public void OnCollision(Entity other) {
        if(other.tag.equals("Player")) {
            // capture
        }
    }
}
