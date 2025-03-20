package Game.UI;

import Engine.Application;
import Engine.Clock;
import Engine.Entities.TextEntity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import Engine.AssetManagement.AssetManager;
import Game.Game;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class MainMenu {
    private TextEntity titleText;
    private TextEntity startText;
    private TextEntity copyrightText;
    private SpriteEntity background;
    private boolean isActive = true;
    private float startTextBaseY = -20;

    public MainMenu() {
        // Create background first so it's behind everything
        background = Application.CreateEntity(new SpriteEntity());
        background.GetSpriteRenderer().sprite = AssetManager.LoadSprite("background/background.png");
        background.transform.position.z = -100; // Put it behind everything
        background.transform.scale = new Vector2f(160, 90); // Full screen size
        
        // Create title text with dramatic scaling
        titleText = Application.CreateEntity(new TextEntity());
        titleText.GetTextRenderer().SetText("E.T. THE EXTRA-TERRESTRIAL");
        titleText.GetTextRenderer().SetColor(new Vector3f(0.2f, 1.0f, 0.2f)); // Green color
        titleText.transform.position.x = -50; // Move to the left
        titleText.transform.position.y =40;
        titleText.transform.position.z = 100; // Make sure it's in front
        titleText.transform.Scale(1.2f);
        
        // Create animated "Press SPACE to Start" text
        startText = Application.CreateEntity(new TextEntity());
        startText.GetTextRenderer().SetText("Press SPACE to Begin Adventure");
        startText.GetTextRenderer().SetColor(new Vector3f(1.0f, 1.0f, 0.2f)); // Yellow color
        startText.transform.position.y = startTextBaseY;
        startText.transform.position.z = 100; // Make sure it's in front
        startText.transform.Scale(0.6f);
        
        // Add copyright text
        copyrightText = Application.CreateEntity(new TextEntity());
        copyrightText.GetTextRenderer().SetText("© 1982 ATARI INC. - REMAKE 2024");
        copyrightText.GetTextRenderer().SetColor(new Vector3f(0.7f, 0.7f, 0.7f)); // Gray color
        copyrightText.transform.position.y = -40;
        copyrightText.transform.position.z = 100; // Make sure it's in front
        copyrightText.transform.Scale(0.4f);
    }

    public void Update() {
        if (!isActive) return;

        // Animate the "Press SPACE" text with a floating effect
        float offset = (float)Math.sin(Clock.GetTimeSinceGameStart() * 2) * 2;
        startText.transform.position.y = startTextBaseY + offset;

        if (Input.GetKeyDown(GLFW.GLFW_KEY_SPACE)) {
            Hide();
            Game.StartGame();
        }
    }

    private void Hide() {
        isActive = false;
        titleText.Destroy();
        startText.Destroy();
        copyrightText.Destroy();
        background.Destroy();
    }
} 