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
        Game.Player.setAction(ActionType.FLY);
    }

    @Override
    public void removeEntities() {
        System.out.println("Hole zone entities removed.");
        Game.Player.setAction(ActionType.NONE);
    }
}
