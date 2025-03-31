package Game.Map;

import Engine.Application;
import Game.ActionType;

public class HoleBG extends Zone {

    public HoleBG() {
        super("HoleBG", "HoleBG.png");
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
