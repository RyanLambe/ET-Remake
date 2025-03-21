package Game;

import Engine.Application;
import Game.Map.ZoneManager;
import Game.UI.MainMenu;
import Game.UI.PauseMenu;
import Game.UI.ScoreDisplay;

public class Game {

    /*public static void Start()
    {
        // this is an example, DO NOT use SpriteEntity Directly, SpriteEntity should be inherited from.
        Player example = Application.CreateEntity(new Player());

        // text examples
        TextEntity text = Application.CreateEntity(new TextEntity());
        text.transform.Scale(0.5f);
        TextEntity text2 = Application.CreateEntity(new TextEntity());
        text2.GetTextRenderer().SetText("How's it going?");
        text2.transform.position.y -= 20;
        text2.transform.position.x -= 50;
        
    }*/
 
    public static ZoneManager zoneManager;
    public static Player player;
    private static MainMenu mainMenu;
    private static PauseMenu pauseMenu;
    private static ScoreDisplay scoreDisplay;
    private static boolean gameStarted = false;
    private static int score = 0;

    public static void Start() {
        // Show main menu first
        mainMenu = new MainMenu();
    }
    
    public static void Update() {
        if (!gameStarted) {
            mainMenu.Update();
        } else {
            // Update pause menu when game is running
            pauseMenu.Update();
        }
    }

    public static void StartGame() {
        gameStarted = true;
        
        zoneManager = new ZoneManager();
        zoneManager.loadZone("Forest");

        // Player setup
        player = Application.CreateEntity(new Player());

        // AI Setup
        AI scientist = Application.CreateEntity(new AI(false, false, 5, 60));
        AI fbi = Application.CreateEntity(new AI(true, true, 3, 50));
        
        // Create pause menu
        pauseMenu = new PauseMenu();

        // Create score display
        scoreDisplay = new ScoreDisplay();
    }

    public static void AddScore(int points) {
        score += points;
        scoreDisplay.UpdateScore(score);
    }
    
    public static Player getPlayer() { return player; }
}
