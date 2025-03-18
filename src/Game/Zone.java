package Game;

import Engine.Application;

public class Zone {
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
    }
    
    public void load(){
        background = Application.CreateEntity(new ZoneBackground(imagePath));
        System.out.println("Loaded zone: " + name);
    }

}
