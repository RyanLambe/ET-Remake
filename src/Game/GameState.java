package Game;

public class GameState {

    public static float stamina = 1;
    public static int reeseCount = 0;
    public static int phonePartsCollected = 0;
    public static ActionType action = ActionType.EAT;
    public static boolean CalledHome = false;

    public static String GetPowerUpString(){
        return switch (action) {
            case EAT -> "Eat A Reese";
            case FLY -> "Fly";
            case CALL_HOME -> "Call Home";
            case NONE -> "ERROR";
        };
    }
}
