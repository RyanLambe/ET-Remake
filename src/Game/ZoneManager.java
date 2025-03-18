package Game;

import Engine.Application;
import java.util.HashMap;

public class ZoneManager {

    private HashMap<String, Zone> zones = new HashMap<>();
    private Zone currentZone;

    public ZoneManager(){
        zones.put("Forest", new Zone("Forest", "forest.PNG"));
        zones.put("Whitehouse", new Zone("Whitehouse", "whitehouse.PNG"));
        zones.put("leftEdge", new Zone("leftEdge", "leftedgeBG.PNG"));
        zones.put("rightEdge", new Zone("rightEdge", "rightEdgeBG.PNG"));
        zones.put("centerRight", new Zone("centerRight", "centerRightBG.PNG"));
        zones.put("centerLeft", new Zone("centerLeft", "centerLeftBG.PNG"));
        zones.put("leftEdge", new Zone("leftEdge", "leftedgeBG.PNG"));
        zones.put("rightEdge", new Zone("rightEdge", "rightEdgeBG.PNG"));
    }


    public void switchZone(String direction){
        String nextZone = null;
        switch(currentZone.name){
            case "Forest":
                if(direction.equals("DOWN")) nextZone = "centerRight";
                break;
            case "leftEdge":
                if(direction.equals("RIGHT")) nextZone = "centerLeft";
                if(direction.equals("UP")) nextZone = "Forest";
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                break;
            case "rightEdge":
                if(direction.equals("LEFT")) nextZone = "centerRight";
                if(direction.equals("UP")) nextZone = "Forest";
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                break;

            case "centerRight":
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("LEFT")) nextZone = "centerLeft";
                else if(direction.equals("RIGHT")) nextZone = "rightEdge";
                break;

            case "centerLeft":
                if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("LEFT")) nextZone = "leftEdge";
                else if(direction.equals("Right")) nextZone = "centerRight";
                break;
            case "Whitehouse":
                if(direction.equals("UP")) nextZone = "centerRight";
                break;
        }

        if(nextZone != null){
            loadZone(nextZone);
        }
    }

    public void loadZone(String zoneName){
        // unload previous zone if any
        if(currentZone != null){
            currentZone.unload();

        }
        // load new zone
        currentZone = zones.get(zoneName);
        currentZone.load();
    }

    public Zone getCurrentZone(){
        return currentZone;
    }
}
