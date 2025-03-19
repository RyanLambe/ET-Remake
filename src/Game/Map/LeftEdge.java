package Game.Map;

import Engine.Application;

public class LeftEdge extends Zone {

    public LeftEdge() {
        super("LeftEdge", "leftedgeBG.PNG");
    }

    @Override
    public void addEntities() {
        System.out.println("LeftEdge zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("LeftEdge zone entities removed.");
    }
}
