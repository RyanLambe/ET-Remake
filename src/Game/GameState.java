package Game;

public class GameState {

    public static float stamina = 1;
    public static int reeseCount = 0;
    public static int phonePartsCollected = 0;
    public static Ability ability = Ability.EatAReese;
    public static boolean CalledHome = false;

    public static String GetPowerUpString(){
        return switch (ability) {
            case EatAReese -> "Eat A Reese";
            case Fly -> "Fly";
            case CallHome -> "Call Home";
        };
    }
}
