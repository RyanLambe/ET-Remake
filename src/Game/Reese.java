package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;

public class Reese extends SpriteEntity {
    private Sprite reeseSprite;

    @Override
    public void Start() {
        // Load Reese's Pieces sprite
        reeseSprite = AssetManager.LoadSprite("reese.png");
        GetSpriteRenderer().sprite = reeseSprite;

        // Enable collisions and set tag
        tag = "Reese";
        collider.enabled = true;
    }

    @Override
    public void OnCollision(Entity other) {
        if (other.tag.equals("Player")) {
            // Add Reese to player's inventory
            Player player = (Player)other;
            player.addReesePiece();

            // Destroy this Reese piece
            Destroy();
        }
    }
}