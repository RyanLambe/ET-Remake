package Game;

import Engine.AssetManagement.AssetManager;
import Engine.AssetManagement.Sprite;
import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Game.UI.PauseMenu;

public class Spaceship extends SpriteEntity {
    private Sprite shipSprite;
    private float speed = 10f;
    private boolean hasLaunched = false;
    private boolean hasPlayer = false;
    private Player attachedPlayer = null;
    private boolean isInitialLaunch;

    @Override
    public void Start() {
        // Load spaceship sprite
        /*shipSprite = AssetManager.LoadSprite("ship.png");
        GetSpriteRenderer().sprite = shipSprite;*/

        String spritePath = "ship.png";
        System.out.println("Attempting to load sprite from: " + spritePath);
        shipSprite = AssetManager.LoadSprite(spritePath);
        
        if (shipSprite == null) {
            System.out.println("Failed to load sprite: " + spritePath);
        } else {
            System.out.println("Successfully loaded sprite: " + spritePath);
        }
        
        GetSpriteRenderer().sprite = shipSprite;
        
        // Set tag and collider
        tag = "Spaceship";

        // Check if this is the initial launch
        isInitialLaunch = Game.player.getPhonePieces() == 0;
        if (isInitialLaunch) {
            hasLaunched = true;
            collider.enabled = false;
            hasPlayer = false; // Make sure player isn't attached
        } else {
            collider.enabled = true;
        }
    }

    @Override
    public void Update() {

        if(!Game.gameStarted)
            return;

        if (hasLaunched) {
            // Move upward with player if attached
            transform.Translate(0, Clock.DeltaTime() * speed, 0);
            if (hasPlayer && attachedPlayer != null) {
                attachedPlayer.transform.position.set(transform.position.x, transform.position.y, 0);
            }

            // Destroy when off screen
            if (transform.position.y > 100) {
                System.out.println("SHip destroyed");
                if (hasPlayer) {
                    Game.zoneManager.switchZone("END"); // Switch to end game zone
                } else if (isInitialLaunch) {
                    Destroy();
                } else {
                    Destroy();
                }
            }
        }
    }

    @Override
    public void OnCollision(Entity other) {
        if (!hasLaunched && other.tag.equals("Player")) {
            hasLaunched = true;
            hasPlayer = true;
            attachedPlayer = (Player)other;
            System.out.println("ET has boarded the spaceship!");
        }
    }
}