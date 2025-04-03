package Game.Map;

import Engine.Application;
import Game.Hole;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CenterLeft extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private Hole hole4;
    private boolean hasPhone1 = false;
    private boolean hasPhone2 = false;
    private boolean hasPhone3 = false;
    private boolean hasPhone4 = false;
    private List<Reese> spawnedReeses = new ArrayList<>(); 

    public CenterLeft() {
        super("CenterLeft", "centerLeftBG.png");
        Random rand = new Random(System.currentTimeMillis()+1);
        int phoneHole = rand.nextInt(4);
        if(phoneHole == 0){
            hasPhone1 = true;
        } else if(phoneHole == 1){
            hasPhone2 = true;
        } else if(phoneHole == 2){
            hasPhone3 = true;
        } else {
            hasPhone4 = true;
        }
    }

    @Override
    public void addEntities() {
        hole1 = Application.CreateEntity(new Hole(-30, 17, hasPhone1));
        hole2 = Application.CreateEntity(new Hole(30, 17, hasPhone2));
        hole3 = Application.CreateEntity(new Hole(-30, -17, hasPhone3));
        hole4 = Application.CreateEntity(new Hole(30, -17, hasPhone4));

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
