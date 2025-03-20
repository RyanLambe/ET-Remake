package Game.Map;

import Engine.Application;

public class Forest extends Zone {

    public Forest() {
        super("Forest", "ForestBG.png");
    }

    @Override
    public void addEntities() {
        // Example: Add trees or NPCs
        System.out.println("Forest zone entities added.");
    }

    @Override
    public void removeEntities() {
        // Example: Remove zone-specific objects when leaving
        System.out.println("Forest zone entities removed.");
    }
}
