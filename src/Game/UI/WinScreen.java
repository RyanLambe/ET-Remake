package Game.UI;

import Engine.Application;
import Engine.Entities.Entity;
import Engine.Entities.TextEntity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import Engine.AssetManagement.AssetManager;
import Game.Game;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

public class WinScreen extends Entity {
    private TextEntity winText;
    private TextEntity playAgainText;
    private SpriteEntity background;
    private boolean isActive = false;

    public WinScreen() {
        // Create but hide initially
        CreateScreenElements();
        Hide();
    }

    private void CreateScreenElements() {
        // Green background
        background = Application.CreateEntity(new SpriteEntity());
        background.GetSpriteRenderer().SetColor(new Vector4f(0.2f, 0.8f, 0.2f, 1.0f)); // Green background
        background.transform.position.z = 95; // Behind text
        background.transform.scale = new Vector2f(160, 90); // Full screen size

        // "YOU WIN!" text
        winText = Application.CreateEntity(new TextEntity());
        winText.GetTextRenderer().SetText("YOU WIN!");
        winText.GetTextRenderer().SetColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        winText.transform.position.x = -40;
        winText.transform.position.y = 0;
        winText.transform.position.z = 100;
        winText.transform.Scale(1.2f);

        // "Press SPACE to Play Again" text
        playAgainText = Application.CreateEntity(new TextEntity());
        playAgainText.GetTextRenderer().SetText("Press SPACE to Play Again");
        playAgainText.GetTextRenderer().SetColor(new Vector4f(0.8f, 0.8f, 0.8f, 1.0f));
        playAgainText.transform.position.x = -40;
        playAgainText.transform.position.y = -30;
        playAgainText.transform.position.z = 100;
        playAgainText.transform.Scale(0.6f);
    }

    public void Update() {
        if (!isActive) return;

        if (Input.GetKeyDown(GLFW.GLFW_KEY_SPACE)) {
            Hide();
            Game.RestartGame();
        }
    }

    public void Show() {
        isActive = true;
        background.GetSpriteRenderer().SetColor(new Vector4f(0.2f, 0.8f, 0.2f, 1.0f));
        winText.GetTextRenderer().SetText("YOU WIN!");
        playAgainText.GetTextRenderer().SetText("Press SPACE to Play Again");
    }

    private void Hide() {
        isActive = false;
        background.GetSpriteRenderer().SetColor(new Vector4f(0, 0, 0, 0.0f));
        winText.GetTextRenderer().SetText("");
        playAgainText.GetTextRenderer().SetText("");
    }

    public void Destroy() {
        background.Destroy();
        winText.Destroy();
        playAgainText.Destroy();
    }
} 