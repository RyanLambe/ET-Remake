package Game.UI;

import Engine.Application;
import Engine.Entities.TextEntity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import Engine.AssetManagement.AssetManager;
import Game.Game;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class LoseScreen {
    private TextEntity loseText;
    private TextEntity reasonText;
    private TextEntity playAgainText;
    private SpriteEntity background;
    private boolean isActive = false;

    public LoseScreen() {
        // Create but hide initially
        CreateScreenElements();
        Hide();
    }

    private void CreateScreenElements() {
        // Red background
        background = Application.CreateEntity(new SpriteEntity());
        background.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.2f, 0.2f)); // Red background
        background.transform.position.z = 95; // Behind text
        background.transform.scale = new Vector2f(160, 90); // Full screen size

        // "YOU LOST!" text
        loseText = Application.CreateEntity(new TextEntity());
        loseText.GetTextRenderer().SetText("YOU LOST!");
        loseText.GetTextRenderer().SetColor(new Vector3f(1.0f, 1.0f, 1.0f));
        loseText.transform.position.x = -40;
        loseText.transform.position.y = 20;
        loseText.transform.position.z = 100;
        loseText.transform.Scale(1.2f);

        // Reason text
        reasonText = Application.CreateEntity(new TextEntity());
        reasonText.GetTextRenderer().SetText("You ran out of stamina!");
        reasonText.GetTextRenderer().SetColor(new Vector3f(1.0f, 1.0f, 1.0f));
        reasonText.transform.position.x = -40;
        reasonText.transform.position.y = -10;
        reasonText.transform.position.z = 100;
        reasonText.transform.Scale(0.6f);

        // "Press SPACE to Play Again" text
        playAgainText = Application.CreateEntity(new TextEntity());
        playAgainText.GetTextRenderer().SetText("Press SPACE to Play Again");
        playAgainText.GetTextRenderer().SetColor(new Vector3f(0.8f, 0.8f, 0.8f));
        playAgainText.transform.position.x = -40;
        playAgainText.transform.position.y = -40;
        playAgainText.transform.position.z = 100;
        playAgainText.transform.Scale(0.6f);
    }

    public void Update() {
        if (!isActive) return;

        if (Input.GetKeyDown(GLFW.GLFW_KEY_SPACE)) {
            Hide();
            Game.StartGame();
        }
    }

    public void Show() {
        isActive = true;
        background.GetSpriteRenderer().SetColor(new Vector3f(0.8f, 0.2f, 0.2f));
        loseText.GetTextRenderer().SetText("YOU LOST!");
        reasonText.GetTextRenderer().SetText("You ran out of stamina!");
        playAgainText.GetTextRenderer().SetText("Press SPACE to Play Again");
    }

    private void Hide() {
        isActive = false;
        background.GetSpriteRenderer().SetColor(new Vector3f(0, 0, 0));
        loseText.GetTextRenderer().SetText("");
        reasonText.GetTextRenderer().SetText("");
        playAgainText.GetTextRenderer().SetText("");
    }

    public void Destroy() {
        background.Destroy();
        loseText.Destroy();
        reasonText.Destroy();
        playAgainText.Destroy();
    }
} 