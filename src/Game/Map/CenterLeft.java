package Game.Map;

import Engine.Application;
import Game.Hole;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;

public class CenterLeft extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private Hole hole4;
    private List<Reese> spawnedReeses = new ArrayList<>(); 

    public CenterLeft() {
        super("CenterLeft", "centerLeftBG.png");
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(-30, 17));
        hole2 = Application.CreateEntity(new Hole(30, 17));
        hole3 = Application.CreateEntity(new Hole(-30, -17));
        hole4 = Application.CreateEntity(new Hole(30, -17));

        System.out.println("CenterLeft zone entities added.");
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

        System.out.println("CenterLeft zone entities removed.");
    }
}
