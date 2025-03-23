package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import Engine.Application;
import Game.UI.PauseMenu;
import org.joml.Vector3f;
import org.joml.Math;
import org.lwjgl.glfw.GLFW;
import org.joml.Math;

// currently used for engine testing
public class Player extends SpriteEntity {

    private Sprite walkLeft = AssetManager.LoadAnimation("ET/Left/0001.png","ET/Left/0002.png","ET/Left/0003.png","ET/Left/0004.png","ET/Left/0005.png","ET/Left/0006.png","ET/Left/0007.png","ET/Left/0008.png","ET/Left/0009.png","ET/Left/0010.png","ET/Left/0011.png","ET/Left/0012.png","ET/Left/0013.png","ET/Left/0014.png","ET/Left/0015.png","ET/Left/0016.png","ET/Left/0017.png","ET/Left/0018.png");
    private Sprite walkRight = AssetManager.LoadAnimation("ET/Right/0001.png","ET/Right/0002.png","ET/Right/0003.png","ET/Right/0004.png","ET/Right/0005.png","ET/Right/0006.png","ET/Right/0007.png","ET/Right/0008.png","ET/Right/0009.png","ET/Right/0010.png","ET/Right/0011.png","ET/Right/0012.png","ET/Right/0013.png","ET/Right/0014.png","ET/Right/0015.png","ET/Right/0016.png","ET/Right/0017.png","ET/Right/0018.png");
    private Sprite walkUp = AssetManager.LoadAnimation("ET/Up/0001.png","ET/Up/0002.png","ET/Up/0003.png","ET/Up/0004.png","ET/Up/0005.png","ET/Up/0006.png","ET/Up/0007.png","ET/Up/0008.png","ET/Up/0009.png","ET/Up/0010.png","ET/Up/0011.png","ET/Up/0012.png","ET/Up/0013.png","ET/Up/0014.png","ET/Up/0015.png","ET/Up/0016.png","ET/Up/0017.png","ET/Up/0018.png");
    private Sprite walkDown = AssetManager.LoadAnimation("ET/Down/0001.png","ET/Down/0002.png","ET/Down/0003.png","ET/Down/0004.png","ET/Down/0005.png","ET/Down/0006.png","ET/Down/0007.png","ET/Down/0008.png","ET/Down/0009.png","ET/Down/0010.png","ET/Down/0011.png","ET/Down/0012.png","ET/Down/0013.png","ET/Down/0014.png","ET/Down/0015.png","ET/Down/0016.png","ET/Down/0017.png","ET/Down/0018.png");

    private Sprite idleLeft = AssetManager.LoadSprite("ET/left.png");
    private Sprite idleRight = AssetManager.LoadSprite("ET/right.png");
    private Sprite idleUp = AssetManager.LoadSprite("ET/up.png");
    private Sprite idleDown = AssetManager.LoadSprite("ET/down.png");

    enum LastAnimationState {
        Down, Left, Right, Up,
    }
    LastAnimationState lastAnimationState = LastAnimationState.Down;

    private float maxStamina = 1;
    private float staminaRegenRate = .25f;

    private int skipFrames = 2;

    private float speed = 30;
    private boolean escaped;

    @Override
    public void Start() {
        // super.Start() is Unnecessary

        GetSpriteRenderer().SetColor(new Vector3f(1, 1, 1)); // sets tint of sprite

        walkLeft.fps = 10;
        walkRight.fps = 10;
        walkUp.fps = 10;
        walkDown.fps = 10;

        GetSpriteRenderer().sprite = walkLeft;

        tag = "Player";
        collider.enabled = true;
        escaped = false;
        setAction(ActionType.EAT);
    }

    @Override
    public void Update() {

        checkZoneTransition();

        if (PauseMenu.isPaused || AI.isHoldingPlayer || !Game.gameLoaded) {
            GetSpriteRenderer().sprite = idleDown;
            return;
        }

        if(skipFrames > 0){
            skipFrames--;
            return;
        }

        boolean walked = false;
        if(Input.GetKey('W') && !Game.zoneManager.getCurrentZone().name.equals("HoleBG")){
            transform.Translate(0, Clock.DeltaTime() * speed, 0);
            GetSpriteRenderer().sprite = walkUp;
            lastAnimationState = LastAnimationState.Up;
            walked = true;
        }
        if(Input.GetKey('S')){
            transform.Translate(0, -Clock.DeltaTime() * speed, 0);
            GetSpriteRenderer().sprite = walkDown;
            lastAnimationState = LastAnimationState.Down;
            walked = true;
        }
        if(Input.GetKey('D')){
            transform.Translate(Clock.DeltaTime() * speed, 0, 0);
            GetSpriteRenderer().sprite = walkRight;
            lastAnimationState = LastAnimationState.Right;
            walked = true;
        }
        if(Input.GetKey('A')){
            transform.Translate(-Clock.DeltaTime() * speed, 0, 0);
            GetSpriteRenderer().sprite = walkLeft;
            lastAnimationState = LastAnimationState.Left;
            walked = true;
        }

        if(!walked){
            switch (lastAnimationState) {
                case Up:
                    GetSpriteRenderer().sprite = idleUp;
                    break;
                case Down:
                    GetSpriteRenderer().sprite = idleDown;
                    break;
                case Left:
                    GetSpriteRenderer().sprite = idleLeft;
                    break;
                case Right:
                    GetSpriteRenderer().sprite = idleRight;
                    break;
            }
        }

        if(Input.GetKey('E'))
            transform.Rotate(Clock.DeltaTime());
        if(Input.GetKey('Q'))
            transform.Rotate(-Clock.DeltaTime());

        if(Input.GetKey('R'))
            transform.Scale(1 + Clock.DeltaTime());
        if(Input.GetKey('F'))
            transform.Scale(1 - Clock.DeltaTime());

        // will only trigger once at the beginning of a key press (same with GetKeyUp except at the end of the key press)
        
        if (GameState.action == ActionType.FLY) {
            if(Input.GetKey(GLFW.GLFW_KEY_SPACE)){
                performAction();
            }
        } else if (GameState.action == ActionType.CALL_HOME) {
            if(Input.GetKey(GLFW.GLFW_KEY_SPACE)){
                performAction();
            }
        } else if (GameState.action == ActionType.EAT) {
            if(Input.GetKeyDown(GLFW.GLFW_KEY_SPACE)){
                performAction();
            }

        }


        if(escaped == true){
            //transform.position.set(0, 25, 0);
            escaped = false;
        }


        if(Game.zoneManager.getCurrentZone().name.equals("HoleBG")){
            transform.position.x = Math.clamp(transform.position.x, -40, 45);
            transform.position.y = Math.max(transform.position.y, -30);
        }

        if(Game.zoneManager.getCurrentZone().name.equals("Whitehouse")){
            transform.position.x = Math.clamp(transform.position.x, -80, 80);
            transform.position.y = Math.max(transform.position.y, -30);
        }

        if(Game.zoneManager.getCurrentZone().name.equals("LeftEdge")){
            transform.position.x = Math.max(transform.position.x, -80);
        }

        if(Game.zoneManager.getCurrentZone().name.equals("RightEdge")){
            transform.position.x = Math.min(transform.position.x, 80);
        }

    }

    @Override
    public void OnCollision(Entity other) {
        if(AI.isHoldingPlayer)
            return;

        if (other.tag.equals("Hole")) {
            if(((Hole)other).hasPhone)
                PhonePiece.show = true;
            fallIntoHole();
        }
    }

    private void checkZoneTransition() {
        float x = transform.position.x;
        float y = transform.position.y;

        if (x > 80) {
            transform.position.x = -60;
            Game.zoneManager.switchZone("RIGHT");
            setAction(ActionType.EAT);
        } else if (x < -80) {
            transform.position.x = 60;
            Game.zoneManager.switchZone("LEFT");
            setAction(ActionType.EAT);
        }

        if (y > 45) {
            transform.position.y = -25;
            Game.zoneManager.switchZone("UP");
            setAction(ActionType.EAT);
        } else if (y < -30) {
            transform.position.y = 35;
            Game.zoneManager.switchZone("DOWN");
            setAction(ActionType.EAT);
        }

        if (y > 45 && Game.zoneManager.getCurrentZone().name.equals("HoleBG")) {
            System.out.println("ET Escaped!");
            Game.zoneManager.switchZone("OUT");
            transform.position.set(0, 0, 0);  // Set position immediately upon escape
            setAction(ActionType.EAT);
        }
    }

    public void addPhonePiece() {
        GameState.phonePartsCollected++;
        System.out.println("Phone pieces collected: " + GameState.phonePartsCollected);
    }

    public int getPhonePieces() {
        return GameState.phonePartsCollected;
    }

    public void addReesePiece() {
        GameState.reeseCount++;
        System.out.println("Reese's Pieces collected: " + GameState.reeseCount);
    }

    public float getStamina() {
        return GameState.stamina;
    }

    public float getMaxStamina() {
        return maxStamina;
    }

    public void useStamina(float amount) {
        GameState.stamina = Math.max(0, GameState.stamina - amount);
    }

    private void fallIntoHole() {
        System.out.println("ET fell into a hole");
        transform.position.set(0, -25, 0); // Move to fall position
        Game.zoneManager.switchZone("FALL");
        escaped = false;  // Reset escape flag immediately
    }


    private void performAction() {
        switch(GameState.action) {
            case FLY:
                fly();
                break;
            case CALL_HOME:
                callHome();
                break;
            default:
                eat();
        }
    }

    // Action methods
    private void fly() {
        transform.Translate(0, Clock.DeltaTime() * speed, 0);
    }

    private void eat() {
        if (GameState.reeseCount > 0) {
            GameState.reeseCount--;
            System.out.println("ET is eating! " + GameState.reeseCount + " left.");
        } else {
            System.out.println("No Reese's Pieces left to eat!");
        }
    }

    private void callHome() {

        if (GameState.phonePartsCollected >= 3) {
            System.out.println("ET is calling home! (Signal Sent)");
            Spaceship spaceship = Application.CreateEntity(new Spaceship());
            spaceship.transform.position.set(0, 0, 0);
            GameState.phonePartsCollected = 0;
        } else {
            System.out.println("Not enough phone pieces to call home!");
        }
    }

    //change the current action
    public static void setAction(ActionType action) {
        GameState.action = action;
        System.out.println("Action changed to: " + action);
    }
}
