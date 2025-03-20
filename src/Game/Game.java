package Game;

import java.util.HashMap;

import Engine.Application;
import Engine.Entities.TextEntity;
import Game.Map.ZoneManager;

public class Game {

    /*public static void Start()
    {
        // this is an example, DO NOT use SpriteEntity Directly, SpriteEntity should be inherited from.
        Player example = Application.CreateEntity(new Player());

        // text examples
        TextEntity text = Application.CreateEntity(new TextEntity());
        text.transform.Scale(0.5f);
        TextEntity text2 = Application.CreateEntity(new TextEntity());
        text2.GetTextRenderer().SetText("How's it going?");
        text2.transform.position.y -= 20;
        text2.transform.position.x -= 50;
        
    }*/
 
    public static ZoneManager zoneManager;
    public static Player player;

    public static void Start() {
        
        zoneManager = new ZoneManager();

        zoneManager.loadZone("Forest");

        // Player setup
        Player example = Application.CreateEntity(new Player());
    }
    
    public static Player getPlayer() { return player; }
}
