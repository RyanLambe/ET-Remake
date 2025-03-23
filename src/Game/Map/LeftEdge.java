package Game.Map;

import java.util.ArrayList;

import Engine.Application;
import Game.Hole;
import Game.Reese;
import java.util.List;

public class LeftEdge extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private Hole hole4; 
    private List<Reese> spawnedReeses = new ArrayList<>();

    public LeftEdge() {
        super("LeftEdge", "leftedgeBG.png");
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(20, 4, false));
        hole2 = Application.CreateEntity(new Hole(-10, 18, false));
        hole3 = Application.CreateEntity(new Hole(-50, 2, false));
        hole4 = Application.CreateEntity(new Hole(-10, -12, false));

        System.out.println("LeftEdge zone entities added.");
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

        System.out.println("LeftEdge zone entities removed.");
    }
}
