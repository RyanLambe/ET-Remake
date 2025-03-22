package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import Game.UI.PauseMenu;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.vulkan.EXTAttachmentFeedbackLoopDynamicState;

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

    private static ActionType currentAction = ActionType.NONE;
    private int phonePieces;
    
        private float speed = 50;
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

            phonePieces = 0;

            tag = "Player";
            collider.enabled = true;
            escaped = false;
        }
    
        @Override
        public void Update() {

            checkZoneTransition();
            System.out.println(transform.position);

            if (PauseMenu.isPaused || AI.isHoldingPlayer) {
                GetSpriteRenderer().sprite = idleDown;
                return;
            }

            boolean walked = false;
            if(Input.GetKey('W') &&!Game.zoneManager.getCurrentZone().name.equals("HoleBG")){
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
            if(Input.GetKey(GLFW.GLFW_KEY_SPACE)){
                performAction();
            }


            if(escaped == true){
                transform.position.set(0, 25, 0);
                escaped = false;
            }
    
            
        }
    
        @Override
        public void OnCollision(Entity other) {
            if(AI.isHoldingPlayer)
                return;

            if (other.tag.equals("Hole")) {
                fallIntoHole();
            }
        }
    
        private void checkZoneTransition() {
            float x = transform.position.x;
            float y = transform.position.y;
        
            if (x > 80) {
                transform.position.x = -60;
                Game.zoneManager.switchZone("RIGHT");
            } else if (x < -80) {
                transform.position.x = 60;
                Game.zoneManager.switchZone("LEFT");
            }
        
            if (y > 45) {
                transform.position.y = -35;
                Game.zoneManager.switchZone("UP");
            } else if (y < -45) {
                transform.position.y = 35;
                Game.zoneManager.switchZone("DOWN");
            }
        
            if (y > 45 && Game.zoneManager.getCurrentZone().name.equals("HoleBG")) {
                System.out.println("ET Escaped!");
                Game.zoneManager.switchZone("OUT");
                transform.position.set(0, 25, 0);  // Set position immediately upon escape
            }
        }
        

        private void fallIntoHole() {
            System.out.println("ET fell into a hole");
            transform.position.set(0, -25, 0); // Move to fall position
            Game.zoneManager.switchZone("FALL");
            escaped = false;  // Reset escape flag immediately
        }
        
        
        private void performAction() {
            switch(currentAction) {
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
            //System.out.println("ET is eating!");
        }
    
        private void callHome() {
     
            if (phonePieces >= 3) {
                System.out.println("ET is calling home! (Signal Sent)");
            } else {
                System.out.println("Not enough phone pieces to call home!");
            }
        }
    
        //change the current action
        public static void setAction(ActionType action) {
            currentAction = action;
            System.out.println("Action changed to: " + action);
        }
}
