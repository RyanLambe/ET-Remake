package Engine;

import Engine.Entities.Entity;
import Engine.Physics.Physics;
import Game.Game;

import Engine.Graphics.*;

import java.util.ArrayList;

public class Application {

    private static final ArrayList<Entity> entities = new ArrayList<>();
    private static boolean gameStarted = false;

    public static void main(String[] args) {
        Window.Create("ET Remake", 1280, 720);
        Graphics.Start();
        Game.Start();

        for(Entity entity : entities){
            entity.Start();
        }

        gameStarted = true;
        while(!Window.ShouldClose()){
            Input.Update();
            Physics.Update();

            // Update game state
            Game.Update();

            // Update all entities
            ArrayList<Entity> lockedEntities = new ArrayList<>(entities);
            for (Entity entity : lockedEntities) {
                entity.Update();
            }

            Clock.CalculateDeltaTime();
            Graphics.Render();
            Window.Update();
        }

        Graphics.Destroy();
        Window.Destroy();
    }


    public static <T extends Entity> T CreateEntity(T entity){
        entity.Setup();
        entities.add(entity);
        if(gameStarted)
            entity.Start();
        return entity;
    }

    public static ArrayList<Entity> GetEntities(){
        return entities;
    }

    public static void RemoveEntityInternal(Entity entity){
        entities.remove(entity);
    }
}