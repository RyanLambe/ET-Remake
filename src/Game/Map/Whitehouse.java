package Game.Map;

import Engine.Application;

public class Whitehouse extends Zone {

    public Whitehouse() {
        super("Whitehouse", "WhitehouseBG.png");
    }

    @Override
    public void addEntities() {
        System.out.println("Whitehouse zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("Whitehouse zone entities removed.");
    }
}
