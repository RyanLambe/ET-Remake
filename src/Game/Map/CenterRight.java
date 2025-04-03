package Game.Map;

import Engine.Application;
import Engine.Clock;
import Game.Hole;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CenterRight extends Zone {

    private Hole hole1;
    private Hole hole2;
    private Hole hole3; 
    private boolean hasPhone1 = false;
    private boolean hasPhone2 = false;
    private boolean hasPhone3 = false;
    private List<Reese> spawnedReeses = new ArrayList<>();
     
    public CenterRight() {
        super("CenterRight", "centerRightBG.png");
        Random rand = new Random(System.currentTimeMillis()+2);
        int phoneHole = rand.nextInt(3);
        if(phoneHole == 0){
            hasPhone1 = true;
        } else if(phoneHole == 1){
            hasPhone2 = true;
        } else{
            hasPhone3 = true;
        }
    }

    @Override
    public void addEntities() {

        hole1 = Application.CreateEntity(new Hole(42, 12, hasPhone1));
        hole2 = Application.CreateEntity(new Hole(-42, 12, hasPhone2));
        hole3 = Application.CreateEntity(new Hole(-30, -10, hasPhone3));



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
