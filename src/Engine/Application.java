package Engine;

import Game.Game;

import Engine.Graphics.*;

public class Application {

    public static void main(String[] args) {
        Window.Create();
        // Graphics.Start()
        // Game.Start()

        Game game = new Game();
        game.Setup();
        while(!Window.ShouldClose()){
            // Physics Update
            // Game Update

            Clock.CalculateDeltaTime();

            Graphics.Render();
            Window.Update();

        }

        Window.Destroy();
    }
}