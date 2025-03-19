package Game.Map;

import Engine.Application;
import Game.ZoneBackground;

public abstract class Zone {
    public String name;
    public ZoneBackground background;
    private String imagePath;

    public Zone(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public void unload() {
        if(background != null){
            Application.RemoveEntityInternal(background);
        }
        removeEntities();
    }
    
    public void load(){
        background = Application.CreateEntity(new ZoneBackground(imagePath));
        System.out.println("Loaded zone: " + name);
        addEntities(); // Loads zone-specific entities
    }

    public abstract void addEntities();
    public abstract void removeEntities();

}
