package Game.Map;

import Engine.Application;

public class RightEdge extends Zone {

    public RightEdge() {
        super("RightEdge", "rightEdgeBG.png");
    }

    @Override
    public void addEntities() {
        System.out.println("RightEdge zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("RightEdge zone entities removed.");
    }
}
