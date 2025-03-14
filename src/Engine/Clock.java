package Engine;

public class Clock {

    private static long lastTime = System.nanoTime();
    private static float deltaTime;
    private static final float globalStartTime = System.nanoTime();

    private long startTime;
    private boolean playing = true;
    private long pauseTime;

    public Clock() {
        Reset();
    }

    public void Reset(){
        startTime = System.nanoTime();
    }

    public void Pause() {
        if(!playing)
            return;
        pauseTime = System.nanoTime();
        playing = false;
    }

    public void Resume() {
        if(playing)
            return;

        startTime += System.nanoTime() - pauseTime;
        playing = true;
    }

    public void TogglePlay(){
        if(playing)
            Pause();
        else
            Resume();
    }

    // returns time since started in seconds
    public float GetTime(){
        if(playing)
            return (System.nanoTime() - startTime) / 1000000000.0f;
        else
            return (pauseTime - startTime) / 1000000000.0f;
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
