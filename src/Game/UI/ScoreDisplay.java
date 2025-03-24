package Game.UI;

import Engine.Application;
import Engine.Entities.SpriteEntity;
import Engine.Entities.TextEntity;
import Engine.AssetManagement.AssetManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class ScoreDisplay {
    private SpriteEntity container;
    private TextEntity scoreText;
    private TextEntity controlsText;
    private SpriteEntity scoreIcon;
    private SpriteEntity spacebarIcon;
    private SpriteEntity staminaBarBackground;
    private SpriteEntity staminaBarFill;
    private int currentScore = 0;

    public ScoreDisplay() {
        // Create container background
        container = Application.CreateEntity(new SpriteEntity());
        container.GetSpriteRenderer().sprite = AssetManager.LoadSprite("UI/score_container.png");
        container.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.2f, 0.2f)); // Dark gray background
        container.transform.position.y = -40; // Position at bottom
        container.transform.position.z = 90; // Behind text, in front of game
        container.transform.scale = new Vector2f(160, 10); // Width of screen, 10 units tall

        // Create spacebar icon behind the text
        spacebarIcon = Application.CreateEntity(new SpriteEntity());
        spacebarIcon.GetSpriteRenderer().sprite = AssetManager.LoadSprite("background/SpacebarIcon.png");
        spacebarIcon.transform.position.x = 2; // Moved left to center with text
        spacebarIcon.transform.position.y = -41;
        spacebarIcon.transform.position.z = 90.5f; // Between container and text
        spacebarIcon.transform.scale = new Vector2f(30, 15); // Adjusted size

        // Create controls text in the middle
        controlsText = Application.CreateEntity(new TextEntity());
        controlsText.GetTextRenderer().SetText("Press SPACE to fly");
        controlsText.GetTextRenderer().SetColor(new Vector3f(0.0f, 0.0f, 0.0f));
        controlsText.transform.position.x = -10; // Centered with icon
        controlsText.transform.position.y = -40; // Match container position
        controlsText.transform.position.z = 91; // In front of container
        controlsText.transform.Scale(0.5f);

        // Create Reese's icon
        scoreIcon = Application.CreateEntity(new SpriteEntity());
        scoreIcon.GetSpriteRenderer().sprite = AssetManager.LoadSprite("/reese.png");
        scoreIcon.transform.position.x = 55;
        scoreIcon.transform.position.y = -37;
        scoreIcon.transform.position.z = 91;

        // Create score text on the right
        scoreText = Application.CreateEntity(new TextEntity());
        scoreText.GetTextRenderer().SetText(": " + currentScore);
        scoreText.GetTextRenderer().SetColor(new Vector3f(1.0f, 1.0f, 1.0f));
        scoreText.transform.position.x = 60;
        scoreText.transform.position.y = -37;
        scoreText.transform.position.z = 91;
        scoreText.transform.Scale(0.5f);

        // Create stamina bar background
        staminaBarBackground = Application.CreateEntity(new SpriteEntity());
        staminaBarBackground.GetSpriteRenderer().SetColor(new Vector3f(0.3f, 0.3f, 0.3f));
        staminaBarBackground.transform.position.x = 60;
        staminaBarBackground.transform.position.y = -42; // Below score text
        staminaBarBackground.transform.position.z = 91;
        staminaBarBackground.transform.scale = new Vector2f(20, 2); // Thinner bar

        // Create stamina bar fill
        staminaBarFill = Application.CreateEntity(new SpriteEntity());
        staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.8f, 0.2f)); // Green
        staminaBarFill.transform.position.x = 60;
        staminaBarFill.transform.position.y = -42; // Match background position
        staminaBarFill.transform.position.z = 92;
        staminaBarFill.transform.scale = new Vector2f(20, 2); // Match background height
    }

    public void UpdateScore(int newScore) {
        currentScore = newScore;
        scoreText.GetTextRenderer().SetText(": " + currentScore);
    }

    public void UpdateStamina(float staminaPercentage) {
        // Update the width of the stamina bar fill based on percentage (0.0 to 1.0)
        staminaBarFill.transform.scale.x = 20 * staminaPercentage;
        
        // Update color based on stamina level
        if (staminaPercentage > 0.6f) {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.8f, 0.2f)); // Green
        } else if (staminaPercentage > 0.3f) {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.8f, 0.2f)); // Yellow
        } else {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.2f, 0.2f)); // Red
        }
    }

    public void Destroy() {
        container.Destroy();
        scoreText.Destroy();
        controlsText.Destroy();
        scoreIcon.Destroy();
        spacebarIcon.Destroy();
        staminaBarBackground.Destroy();
        staminaBarFill.Destroy();
    }
} 