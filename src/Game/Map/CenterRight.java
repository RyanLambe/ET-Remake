package Game.Map;

import Engine.Application;
import Game.Hole;

public class CenterRight extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
     
    public CenterRight() {
        super("CenterRight", "centerRightBG.png");
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(42, 12));    
        hole2 = Application.CreateEntity(new Hole(-42, 12));   
        hole3 = Application.CreateEntity(new Hole(3, -17));   

        System.out.println("CenterRight zone entities added.");
    }

    @Override
    public void removeEntities() {
        if (hole1 != null) hole1.Destroy();
        if (hole2 != null) hole2.Destroy();
        if (hole3 != null) hole3.Destroy();
        System.out.println("CenterRight zone entities removed.");
    }
}
