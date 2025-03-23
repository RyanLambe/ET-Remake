package Game.Map;

import Engine.Application;
import Game.Hole;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;

public class RightEdge extends Zone {
    
    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private Hole hole4; 
    private List<Reese> spawnedReeses = new ArrayList<>();

    public RightEdge() {
        super("RightEdge", "rightEdgeBG.png");
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(0, 20));    
        hole2 = Application.CreateEntity(new Hole(38, -2));   
        hole3 = Application.CreateEntity(new Hole(0, -25));   
        hole4 = Application.CreateEntity(new Hole(-26, -1));    

        System.out.println("RightEdge zone entities added.");
    }

    @Override
    public void removeEntities() {
        if (hole1 != null) hole1.Destroy();
        if (hole2 != null) hole2.Destroy();
        if (hole3 != null) hole3.Destroy();
        if (hole4 != null) hole4.Destroy();

        // Clean up Reeses
        for (Reese reese : spawnedReeses) {
            if (reese != null) {
                reese.Destroy();
            }
        }
        spawnedReeses.clear();
        
        System.out.println("RightEdge zone entities removed.");
    }
}
