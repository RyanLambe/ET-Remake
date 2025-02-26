package Engine;

import Game.Game;

import Engine.Graphics.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Application {

    public static void main(String[] args) {
        Window.Create();
        // Graphics.Start()
        // Game.Start()

        Game game = new Game();
        game.Setup();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f); // move to Graphics
        while(!Window.ShouldClose()){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // move to Graphics

            // Physics Update
            // Game Update

            Clock.CalculateDeltaTime();

            Graphics.Update();
            Window.Update();

        }

        Window.Destroy();
    }
}