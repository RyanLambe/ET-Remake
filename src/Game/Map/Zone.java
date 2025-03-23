package Game.Map;

import Engine.Application;
import Game.Reese;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Zone {
    public String name;
    public ZoneBackground background;
    private String imagePath;
    protected List<Reese> spawnedReeses = new ArrayList<>();
    protected Random random = new Random();
    protected static final float REESE_SPAWN_CHANCE = 0.3f; // 30% chance to spawn

    public Zone(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public void unload() {
        if(background != null){
            Application.RemoveEntityInternal(background);
        }
        removeEntities();

        // Clean up all Reeses when unloading
        cleanupReeses();
    }
    
    
    
    protected void trySpawnReese() {

        if (this instanceof LeftEdge || 
            this instanceof CenterLeft || 
            this instanceof CenterRight || 
            this instanceof RightEdge) {

            if (random.nextFloat() < REESE_SPAWN_CHANCE) {
                Reese reese = Application.CreateEntity(new Reese());
                // Random position within reasonable bounds
                float x = random.nextFloat() * 120 - 60; // -60 to +60
                float y = random.nextFloat() * 60 - 30;  // -30 to +30
                reese.transform.position.set(x, y, 0);
                spawnedReeses.add(reese); // Track spawned Reeses
                System.out.println("Spawned Reese's at: " + x + ", " + y);
            }
        }
    }

    protected void cleanupReeses() {
        for (Reese reese : spawnedReeses) {
            if (reese != null) {
                reese.Destroy();
            }
        }
        spawnedReeses.clear();
    }
    
    public void load() {
        background = Application.CreateEntity(new ZoneBackground(imagePath));
        System.out.println("Loaded zone: " + name);
        addEntities(); // Loads zone-specific entities
        trySpawnReese(); // Try to spawn Reese's when loading zone
    }

    public abstract void addEntities();
    public abstract void removeEntities();

    

}
