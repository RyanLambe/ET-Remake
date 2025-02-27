package Engine;

import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Game.Game;

import Engine.Graphics.*;

import java.util.ArrayList;

public class Application {

    private static final ArrayList<Entity> entities = new ArrayList<Entity>();

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
        entities.add(entity);
        entities.get(entities.size() - 1).Setup();
        return entity;
    }

    public static ArrayList<Entity> GetEntities(){
        return entities;
    }
}