package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import org.joml.Math;
import org.joml.Vector2f;

public class PhonePiece extends SpriteEntity {
    private Sprite phoneSprite;

    public static boolean show = false;

    @Override
    public void Start() {
        // Load phone piece sprite
        phoneSprite = AssetManager.LoadSprite("PhoneComplete.png");
        GetSpriteRenderer().sprite = phoneSprite;

        // Enable collisions and set tag
        tag = "PhonePiece";
        collider.enabled = true;
        transform.position.x = -25;
    }

    @Override
    public void Update() {
        //

        if(show){
            transform.position.y = -30;
        }
        else{
            transform.position.y = -2000;
        }
    }

    @Override
    public void OnCollision(Entity other) {
        if (other.tag.equals("Player")) {
            // Add phone piece to player
            Player player = (Player)other;
            player.addPhonePiece();

            // Destroy phone piece after collecting
            Destroy();
        }
    }
}