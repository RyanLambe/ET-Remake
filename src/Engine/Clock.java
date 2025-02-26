package Engine;

public class Clock {

    private static long lastTime = System.nanoTime();
    private static double deltaTime;

    private long startTime;

    Clock() {
        Reset();
    }

    public void Reset(){
        startTime = System.nanoTime();
    }

    // returns time since started in seconds
    public double GetTime(){
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    // returns time since last frame in seconds
    public static double DeltaTime(){
        return deltaTime;
    }

    public static void CalculateDeltaTime(){

        long current = System.nanoTime();
        deltaTime = (current - lastTime) / 1000000000.0;
        lastTime = current;
    }
}
