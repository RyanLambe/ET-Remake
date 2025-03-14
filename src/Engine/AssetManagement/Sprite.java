package Engine.AssetManagement;

import Engine.Clock;
import Engine.Graphics.Graphics;

import java.util.ArrayList;

public class Sprite {
    public ArrayList<Image> frames;
    public float fps;

    Clock frameClock;

    public Sprite() {
        this.frames = new ArrayList<>();
        frameClock = new Clock();
        fps = Graphics.GetDefaultFPS();
    }

    public void AddFrame(Image frame) {
        frames.add(frame);
    }

    public ArrayList<Image> GetFrames() {
        return frames;
    }

    public Image GetNextFrame() {
        int framesPassed = (int) (frameClock.GetTime() * fps);
        return frames.get(framesPassed % frames.size());
    }

}
