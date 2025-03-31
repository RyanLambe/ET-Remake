package Game;

import Engine.Application;
import Engine.Entities.SpriteEntity;
import Engine.Physics.Physics;
import Game.Map.ZoneManager;
import Game.UI.*;

public class Game {

    public static ZoneManager zoneManager;
    public static Player player;
    private static MainMenu mainMenu;
    private static PauseMenu pauseMenu;
    private static LoseScreen loseScreen;
    private static WinScreen winScreen;
    private static ScoreDisplay scoreDisplay;
    private static boolean gameStarted = false;
    public static boolean gameLoaded = false;

    static AI scientist;
    static AI fbi;
    static PhonePiece phonePiece;

    public static void Start() {
        // Show main menu first
        mainMenu = new MainMenu();
        loseScreen = Application.CreateEntity(new LoseScreen());
        winScreen = Application.CreateEntity(new WinScreen());
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
        // Player setup
        player = Application.CreateEntity(new Player());

        zoneManager.loadZone("Forest");

        // Create spaceship
        Spaceship initialShip = Application.CreateEntity(new Spaceship());
        initialShip.transform.position.set(0, 0, 0);


        // AI Setup
        scientist = Application.CreateEntity(new AI(false, false, 5, 90, false));
        fbi = Application.CreateEntity(new AI(true, true, 3, 80, true));

        // create phone pieces
        phonePiece = Application.CreateEntity(new PhonePiece());
        
        // Create pause menu
        pauseMenu = new PauseMenu();

        // Create score display
        scoreDisplay = Application.CreateEntity(new ScoreDisplay());

        gameLoaded = true;
    }

    public static void RestartGame(){
        gameStarted = true;

        // gamestate
        GameState.stamina = 1.0f;
        GameState.phonePartsCollected = 0;
        GameState.reeseCount = 0;
        GameState.CalledHome = false;

        player.transform.position.set(0, 0, 0);
        zoneManager.loadZone("Forest");

        // Create spaceship
        Spaceship initialShip = Application.CreateEntity(new Spaceship());
        initialShip.transform.position.set(0, 0, 0);

        // AI Setup
        scientist = new AI(false, false, 5, 90, false);
        fbi = new AI(true, true, 3, 80, true);

        gameLoaded = true;
    }
    
    public static Player getPlayer() { return player; }

    public static void Lose(){
        loseScreen.Show();
    }

    public static void Win(){
        winScreen.Show();
    }
}
