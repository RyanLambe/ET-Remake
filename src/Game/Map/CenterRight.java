package Game.Map;

import Engine.Application;
import Game.Hole;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;

public class CenterRight extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private List<Reese> spawnedReeses = new ArrayList<>();
     
    public CenterRight() {
        super("CenterRight", "centerRightBG.png");
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(42, 12, false));
        hole2 = Application.CreateEntity(new Hole(-42, 12, false));
        hole3 = Application.CreateEntity(new Hole(-30, -10, true));

        System.out.println("CenterRight zone entities added.");
    }

    @Override
    public void removeEntities() {
        if (hole1 != null) hole1.Destroy();
        if (hole2 != null) hole2.Destroy();
        if (hole3 != null) hole3.Destroy();

        // Clean up Reeses
        for (Reese reese : spawnedReeses) {
            if (reese != null) {
                reese.Destroy();
            }
        }
        spawnedReeses.clear();

        System.out.println("CenterRight zone entities removed.");
    }
}
