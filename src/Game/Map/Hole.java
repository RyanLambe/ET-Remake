package Game.Map;

import Engine.Application;

public class Hole extends Zone {

    public Hole() {
        super("Hole", "Hole.png");
    }

    @Override
    public void addEntities() {
        System.out.println("Hole zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("Hole zone entities removed.");
    }
}
