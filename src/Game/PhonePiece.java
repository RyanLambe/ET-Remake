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
        // phoneSprite = AssetManager.LoadSprite("PhoneComplete.png");
        updatePhoneSprite();
        GetSpriteRenderer().sprite = phoneSprite;

        // Enable collisions and set tag
        tag = "PhonePiece";
        collider.enabled = true;
        transform.position.x = -25;
    }

    @Override
    public void Update() {
        //


        if ((!Game.zoneManager.getCurrentZone().name.equals("HoleBG")) || (GameState.phonePartsCollected == 3)) {
            show = false;
        }

        if (show) {
            transform.position.y = -20;
        } else {
            transform.position.y = -2000;
        }
    }

    @Override
    public void OnCollision(Entity other) {
        if (other.tag.equals("Player")) {
            // Add phone piece to player
            Player player = (Player)other;
            player.addPhonePiece();

            // Update phone sprite for next piece
            updatePhoneSprite();

            // Destroy phone piece after collecting
            show = false;
        }
    }

    private void updatePhoneSprite() {
        switch (GameState.phonePartsCollected) {
            case 0:
                phoneSprite = AssetManager.LoadSprite("Phone1.png");
                break;
            case 1:
                phoneSprite = AssetManager.LoadSprite("Phone2.png");
                break;
            case 2:
                phoneSprite = AssetManager.LoadSprite("Phone3.png");
                break;
            default:
                phoneSprite = AssetManager.LoadSprite("PhoneComplete.png");
                break;
        }
        GetSpriteRenderer().sprite = phoneSprite;
    }
}