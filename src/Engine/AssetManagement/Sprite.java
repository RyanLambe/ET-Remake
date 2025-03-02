package Engine.AssetManagement;

import java.util.ArrayList;

public class Sprite {
    public ArrayList<Image> frames;

    public Sprite() {
        this.frames = new ArrayList<>();
    }

    public void addFrame(Image frame) {
        frames.add(frame);
    }

    public ArrayList<Image> getFrames() {
        return frames;
    }

}
