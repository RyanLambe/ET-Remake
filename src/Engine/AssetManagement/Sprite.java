package Engine.AssetManagement;

import Engine.Clock;
import Engine.Graphics.Graphics;

import java.util.ArrayList;

public class Sprite {
    public ArrayList<Image> frames;

    Clock frameClock;

    public Sprite() {
        this.frames = new ArrayList<>();
        frameClock = new Clock();
    }

    public void addFrame(Image frame) {
        frames.add(frame);
    }

    public ArrayList<Image> getFrames() {
        return frames;
    }

    public Image getNextFrame() {
        int framesPassed = (int) (frameClock.GetTime() * Graphics.GetFPS());
        return frames.get(framesPassed % frames.size());
    }

}
