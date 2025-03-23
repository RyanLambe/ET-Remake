package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;

public class PhonePiece extends SpriteEntity {
    private Sprite phoneSprite;

    @Override
    public void Start() {
        // Load phone piece sprite
        phoneSprite = AssetManager.LoadSprite("ET/phone_piece.png");
        GetSpriteRenderer().sprite = phoneSprite;

        // Enable collisions and set tag
        tag = "PhonePiece";
        collider.enabled = true;
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