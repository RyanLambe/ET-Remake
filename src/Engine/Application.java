package Engine;

import Game.Game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Application {

    public static void main(String[] args) {
        Window.Create();

        Game game = new Game();
        game.Setup();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f); // temp
        while(!Window.ShouldClose()){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // temp

            // Physics Update
            // Game Update
            //Graphics.Update();
            Window.Update();
        }

        Window.Destroy();
    }
}