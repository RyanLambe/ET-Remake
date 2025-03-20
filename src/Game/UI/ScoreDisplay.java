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
    private int currentScore = 0;

    public ScoreDisplay() {
        // Create container background
        container = Application.CreateEntity(new SpriteEntity());
        container.GetSpriteRenderer().sprite = AssetManager.LoadSprite("UI/score_container.png");
        container.GetSpriteRenderer().SetColor(new Vector3f(0.2f, 0.2f, 0.2f)); // Dark gray background
        container.transform.position.y = -40; // Position at bottom
        container.transform.position.z = 90; // Behind text, in front of game
        container.transform.scale = new Vector2f(160, 10); // Width of screen, 10 units tall

        // Create score text
        scoreText = Application.CreateEntity(new TextEntity());
        scoreText.GetTextRenderer().SetText("Score: 0");
        scoreText.GetTextRenderer().SetColor(new Vector3f(1.0f, 1.0f, 1.0f));
        scoreText.transform.position.x = -75; // Align to left side
        scoreText.transform.position.y = -40; // Match container position
        scoreText.transform.position.z = 91; // In front of container
        scoreText.transform.Scale(0.5f);
    }

    public void UpdateScore(int newScore) {
        currentScore = newScore;
        scoreText.GetTextRenderer().SetText("Score: " + currentScore);
    }

    public void Destroy() {
        container.Destroy();
        scoreText.Destroy();
    }
} 