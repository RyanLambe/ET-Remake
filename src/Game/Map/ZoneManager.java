package Game.Map;

import Engine.Application;
import Game.Game;

import java.util.HashMap;

public class ZoneManager {

    private HashMap<String, Zone> zones = new HashMap<>();
    private Zone currentZone;
    private String lastZone;

    public ZoneManager(){
        zones.put("Forest", new Forest());
        zones.put("Whitehouse", new Whitehouse());
        zones.put("LeftEdge", new LeftEdge());
        zones.put("RightEdge", new RightEdge());
        zones.put("CenterRight", new CenterRight());
        zones.put("CenterLeft", new CenterLeft());
        zones.put("HoleBG", new HoleBG());
    }


    public void switchZone(String direction){
      
        String nextZone = null;
        switch(currentZone.name){
            case "Forest":
                lastZone = "Forest";
                if(direction.equals("DOWN")) nextZone = "CenterRight";
                break;

            case "LeftEdge":
                lastZone = "LeftEdge";
                if(direction.equals("RIGHT")) nextZone = "CenterLeft";
                else if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("FALL")) nextZone = "HoleBG";
                break;

            case "RightEdge":
                lastZone = "RightEdge";
                if(direction.equals("LEFT")) nextZone = "CenterRight";
                else if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("FALL")) nextZone = "HoleBG";
                break;

            case "CenterRight":
                lastZone = "CenterRight";
                if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("LEFT")) nextZone = "CenterLeft";
                else if(direction.equals("RIGHT")) nextZone = "RightEdge";
                else if(direction.equals("FALL")) nextZone = "HoleBG";
                break;

            case "CenterLeft":
                lastZone = "CenterLeft";
                if(direction.equals("UP")) nextZone = "Forest";
                else if(direction.equals("DOWN")) nextZone = "Whitehouse";
                else if(direction.equals("LEFT")) nextZone = "LeftEdge";
                else if(direction.equals("RIGHT")) nextZone = "CenterRight";
                else if(direction.equals("FALL")) nextZone = "HoleBG";
                break;

            case "Whitehouse":
                lastZone = "Whitehouse";
                if(direction.equals("UP")) nextZone = "CenterRight";
                break;
            
            case "HoleBG":
                if(direction.equals(("UP"))) nextZone = lastZone;
                Game.getPlayer().transform.position.set(0, 0, 0);
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

    public String getLastZone(){
        return lastZone;
    }

    public void Destroy(){
        zones.values().forEach(Zone::Destroy);
        zones = new HashMap<>();
    }
}
