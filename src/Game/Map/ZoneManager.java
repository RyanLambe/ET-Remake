package Game.Map;

import Engine.Application;

import java.util.HashMap;

public class ZoneManager {

    private HashMap<String, Zone> zones = new HashMap<>();
    private Zone currentZone;

    public ZoneManager(){
        zones.put("Forest", new Forest());
        zones.put("Whitehouse", new Whitehouse());
        zones.put("LeftEdge", new LeftEdge());
        zones.put("RightEdge", new RightEdge());
        zones.put("CenterRight", new CenterRight());
        zones.put("CenterLeft", new CenterLeft());
    }


    public void switchZone(String direction){
        String nextZone = null;
        switch(currentZone.name){
            case "Forest":
                if(direction.equals("DOWN")) nextZone = "CenterRight";
                break;

            case "LeftEdge":
                if(direction.equals("RIGHT")) nextZone = "CenterLeft";
                if(direction.equals("UP")) nextZone = "Forest";
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                break;

            case "RightEdge":
                if(direction.equals("LEFT")) nextZone = "CenterRight";
                if(direction.equals("UP")) nextZone = "Forest";
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                break;

            case "CenterRight":
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("LEFT")) nextZone = "CenterLeft";
                else if(direction.equals("RIGHT")) nextZone = "RightEdge";
                break;

            case "CenterLeft":
                if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("LEFT")) nextZone = "LeftEdge";
                else if(direction.equals("RIGHT")) nextZone = "CenterRight";
                break;

            case "Whitehouse":
                if(direction.equals("UP")) nextZone = "CenterRight";
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
