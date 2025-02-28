package Engine;

import Engine.Entities.Entity;
import Game.Game;

import Engine.Graphics.*;

import java.util.ArrayList;

public class Application {

    private static final ArrayList<Entity> entities = new ArrayList<>();

    public static void main(String[] args) {
        Window.Create();
        Graphics.Start();
        Game.Start();

        while(!Window.ShouldClose()){
            // Physics Update
            // Game Update

            Clock.CalculateDeltaTime();

            Graphics.Render();
            Window.Update();

        }

        Window.Destroy();
    }


    public static <T extends Entity> T CreateEntity(T entity){
        entity.Setup();
        entities.add(entity);
        return entity;
    }

    public static ArrayList<Entity> GetEntities(){
        return entities;
    }

    public static void RemoveEntityInternal(Entity entity){
        entities.remove(entity);
    }
}