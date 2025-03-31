package Game.UI;

import Engine.Application;
import Engine.Entities.TextEntity;
import Engine.Input;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

public class PauseMenu {
    private TextEntity pauseText;
    private TextEntity resumeText;
    private boolean isActive = false;
    
    public static boolean isPaused = false;

    public PauseMenu() {
        // Create but hide initially
        CreateMenuElements();
        Hide();
    }

    private void CreateMenuElements() {
        // "PAUSED" text
        pauseText = Application.CreateEntity(new TextEntity());
        pauseText.GetTextRenderer().SetText("Paused");
        pauseText.GetTextRenderer().SetColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        pauseText.transform.position.x = -30;
        pauseText.transform.position.y = 10;
        pauseText.transform.position.z = 100;
        pauseText.transform.Scale(0.7f);

        // "Press ESC to Resume" text
        resumeText = Application.CreateEntity(new TextEntity());
        resumeText.GetTextRenderer().SetText("Press ESC to Resume");
        resumeText.GetTextRenderer().SetColor(new Vector4f(0.8f, 0.8f, 0.8f, 1.0f));
        resumeText.transform.position.x = -30;
        resumeText.transform.position.y = -10;
        resumeText.transform.position.z = 100;
        resumeText.transform.Scale(0.6f);
    }

    public void Update() {
        if (Input.GetKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            if (isActive) {
                Hide();
            } else {
                Show();
            }
        }
    }

    private void Show() {
        isActive = true;
        isPaused = true;
        pauseText.GetTextRenderer().SetText("PAUSED ");
        resumeText.GetTextRenderer().SetText("Press ESC to Resume");
    }

    private void Hide() {
        isActive = false;
        isPaused = false;
        pauseText.GetTextRenderer().SetText("");
        resumeText.GetTextRenderer().SetText("");
    }

    public void Destroy() {
        pauseText.Destroy();
        resumeText.Destroy();
    }
} 