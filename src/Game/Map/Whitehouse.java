package Game.Map;

import Engine.Application;
import Game.Hole;

public class Whitehouse extends Zone {
    
    private WhitehouseFG fg;

    public Whitehouse() {
        super("Whitehouse", "WhitehouseBG.png");
    }

    @Override
    public void addEntities() {
        fg = Application.CreateEntity(new WhitehouseFG(0, 0));   
        System.out.println("Whitehouse zone entities added.");
    }

    @Override
    public void removeEntities() {
        if (fg != null) fg.Destroy();
        System.out.println("Whitehouse zone entities removed.");
    }
}
