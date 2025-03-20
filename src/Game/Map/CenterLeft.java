package Game.Map;

import Engine.Application;

public class CenterLeft extends Zone {

    public CenterLeft() {
        super("CenterLeft", "centerLeftBG.png");
    }

    @Override
    public void addEntities() {
        System.out.println("CenterLeft zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("CenterLeft zone entities removed.");
    }
}
