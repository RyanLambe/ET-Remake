package Engine;

public class Clock {

    private static long lastTime = System.nanoTime();
    private static float deltaTime;
    private static final float globalStartTime = System.nanoTime();

    private long startTime;

    Clock() {
        Reset();
    }

    public void Reset(){
        startTime = System.nanoTime();
    }

    // returns time since started in seconds
    public float GetTime(){
        return (System.nanoTime() - startTime) / 1000000000.0f;
    }

    // returns time since last frame in seconds
    public static float DeltaTime(){
        return deltaTime;
    }

    public static float GetTimeSinceGameStart(){
        return (System.nanoTime() - globalStartTime) / 1000000000.0f;
    }

    public static void CalculateDeltaTime(){

        long current = System.nanoTime();
        deltaTime = (current - lastTime) / 1000000000.0f;
        lastTime = current;
    }
}
