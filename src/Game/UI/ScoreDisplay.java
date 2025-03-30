package Game.UI;

import Engine.Application;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Entities.TextEntity;
import Engine.AssetManagement.AssetManager;
import Game.ActionType;
import Game.GameState;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class ScoreDisplay extends Entity {
    private SpriteEntity container;
    private TextEntity scoreText;
    private TextEntity controlsText;
    private TextEntity abilityText;
    private SpriteEntity scoreIcon;
    private SpriteEntity spacebarIcon;
    private SpriteEntity staminaBarBackground;
    private SpriteEntity staminaBarFill;
    private SpriteEntity phonePart1;
    private SpriteEntity phonePart2;
    private SpriteEntity phonePart3;
    private int currentScore = 0;

    public ScoreDisplay() {
        // Create container background
        container = Application.CreateEntity(new SpriteEntity());
        container.GetSpriteRenderer().sprite = AssetManager.LoadSprite("UI/score_container.png");
        container.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.2f, 0.2f)); // Dark gray background
        container.transform.position.y = -40; // Position at bottom
        container.transform.position.z = 90; // Behind text, in front of game
        container.transform.scale = new Vector2f(160, 18); // Width of screen, 18 units tall

        // Create spacebar icon behind the text
        spacebarIcon = Application.CreateEntity(new SpriteEntity());
        spacebarIcon.GetSpriteRenderer().sprite = AssetManager.LoadSprite("background/SpacebarIcon.png");
        spacebarIcon.transform.position.x = 0; // Moved left to center with text
        spacebarIcon.transform.position.y = -38;
        spacebarIcon.transform.position.z = 90.5f; // Between container and text
        spacebarIcon.transform.Scale(3.5f);// = new Vector2f(30, 15); // Adjusted size

        // Create controls text in the middle
        controlsText = Application.CreateEntity(new TextEntity());
        controlsText.GetTextRenderer().SetText("Press SPACE to:");
        controlsText.GetTextRenderer().SetColor(new Vector3f(0.0f, 0.0f, 0.0f));
        controlsText.transform.position.x = -15; // Centered with icon
        controlsText.transform.position.y = -33.5f; // Match container position
        controlsText.transform.position.z = 91; // In front of container
        controlsText.transform.Scale(0.5f);

        // Create ability text in the middle
        abilityText = Application.CreateEntity(new TextEntity());
        abilityText.GetTextRenderer().SetText("Call Home");
        abilityText.GetTextRenderer().SetColor(new Vector3f(0.0f, 0.0f, 0.0f));
        abilityText.transform.position.x = -10; // Centered with icon
        abilityText.transform.position.y = -37.5f; // Match container position
        abilityText.transform.position.z = 91; // In front of container
        abilityText.transform.Scale(0.9f);

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
        staminaBarFill.transform.scale = new Vector2f(20, 1); // Match background height

        phonePart1 = Application.CreateEntity(new SpriteEntity());
        phonePart1.GetSpriteRenderer().sprite = AssetManager.LoadSprite("Phone1.png");
        phonePart1.transform.position.x = -50;
        phonePart1.transform.position.y = -38;
        phonePart1.transform.position.z = 90.5f;
        phonePart1.transform.Scale(0);

        phonePart2 = Application.CreateEntity(new SpriteEntity());
        phonePart2.GetSpriteRenderer().sprite = AssetManager.LoadSprite("Phone2.png");
        phonePart2.transform.position.x = -50;
        phonePart2.transform.position.y = -38;
        phonePart2.transform.position.z = 90.5f;
        phonePart2.transform.Scale(0);

        phonePart3 = Application.CreateEntity(new SpriteEntity());
        phonePart3.GetSpriteRenderer().sprite = AssetManager.LoadSprite("Phone3.png");
        phonePart3.transform.position.x = -50;
        phonePart3.transform.position.y = -38;
        phonePart3.transform.position.z = 90.5f;
        phonePart3.transform.Scale(0);
    }

    public void Update()
    {
        UpdateScore(GameState.reeseCount);
        UpdateStamina(GameState.stamina);
        UpdateAbility(GameState.action);
        UpdatePhoneParts(GameState.phonePartsCollected);
    }

    private void UpdateScore(int newScore) {
        currentScore = newScore;
        scoreText.GetTextRenderer().SetText(": " + currentScore);
    }

    private void UpdateStamina(float staminaPercentage) {
        // Update the width of the stamina bar fill based on percentage (0.0 to 1.0)
        staminaBarFill.transform.scale.x = 20 * staminaPercentage;
        staminaBarFill.transform.position.x = 50 + (10 * staminaPercentage);
        
        // Update color based on stamina level
        if (staminaPercentage > 0.6f) {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.8f, 0.2f)); // Green
        } else if (staminaPercentage > 0.3f) {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.8f, 0.2f)); // Yellow
        } else {
            staminaBarFill.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.2f, 0.2f)); // Red
        }
    }

    private void UpdateAbility(ActionType ability) {
        switch(ability){
            case EAT:
                abilityText.GetTextRenderer().SetText("Eat A Reese");
                abilityText.transform.position.x = -15;
                break;
            case FLY:
                abilityText.GetTextRenderer().SetText("Fly");
                abilityText.transform.position.x = -2.5f;
                break;
            case CALL_HOME:
                abilityText.GetTextRenderer().SetText("Call Home");
                abilityText.transform.position.x = -10;
                break;
            case NONE:
                abilityText.GetTextRenderer().SetText("ERROR");
                abilityText.transform.position.x = -15;
                break;
        }
    }

    private void UpdatePhoneParts(int partCount) {
        if(partCount >= 1)
            phonePart1.transform.scale = new Vector2f(15, 15);
        else
            phonePart1.transform.scale = new Vector2f(0, 0);

        if(partCount >= 2)
            phonePart2.transform.scale = new Vector2f(15, 15);
        else
            phonePart2.transform.scale = new Vector2f(0, 0);

        if(partCount >= 3)
            phonePart3.transform.scale = new Vector2f(15, 15);
        else
            phonePart3.transform.scale = new Vector2f(0, 0);
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