package Game;

import Engine.AssetManagement.AssetManager;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

// currently used for engine testing
public class Player extends SpriteEntity {

    private float speed = 50;

    @Override
    public void Start() {
        // super.Start() is Unnecessary

        GetSpriteRenderer().SetColor(new Vector3f(1, 1, 1)); // sets tint of sprite

        GetSpriteRenderer().sprite = AssetManager.LoadSprite("testImage.png"); // sets image for sprite to use
        GetSpriteRenderer().sprite = AssetManager.LoadAnimation("testImage.png", "testImage.png", "testImage2.png"); // sets animation for sprite (you can have as many paths as needed, repeating paths is encouraged)

        GetSpriteRenderer().sprite.fps = 2; // default is 1, this is just showing how to change
    }

    @Override
    public void Update() {
        // super.Update() is Unnecessary
        if(Input.GetKey('W'))
            transform.Translate(0, Clock.DeltaTime() * speed, 0);
        if(Input.GetKey('S'))
            transform.Translate(0, -Clock.DeltaTime() * speed, 0);
        if(Input.GetKey('D'))
            transform.Translate(Clock.DeltaTime() * speed, 0, 0);
        if(Input.GetKey('A'))
            transform.Translate(-Clock.DeltaTime() * speed, 0, 0);

        if(Input.GetKey('E'))
            transform.Rotate(Clock.DeltaTime());
        if(Input.GetKey('Q'))
            transform.Rotate(-Clock.DeltaTime());

        if(Input.GetKey('R'))
            transform.Scale(1 + Clock.DeltaTime());
        if(Input.GetKey('F'))
            transform.Scale(1 - Clock.DeltaTime());

        // will only trigger once at the beginning of a key press (same with GetKeyUp except at the end of the key press)
        if(Input.GetKeyDown(GLFW.GLFW_KEY_SPACE)){
            GetSpriteRenderer().sprite.ToggleAnimation();
        }

        checkZoneTransition();
    }

    @Override
    public void OnCollision(Entity other) {
        // super.OnCollision() is Unnecessary
    }

    private void checkZoneTransition() {
        float x = transform.position.x;
        float y = transform.position.y;

        if(x > 80){
            transform.position.x = -60;
            Game.zoneManager.switchZone("RIGHT");
        }
        else if(x < -80){
            transform.position.x = 60;
            Game.zoneManager.switchZone("LEFT");
        }

        if(y > 45){
            transform.position.y = -35;
            Game.zoneManager.switchZone("UP");
        }
        else if(y < -45){
            transform.position.y = 35;
            Game.zoneManager.switchZone("DOWN");
        }
    }
    
}
