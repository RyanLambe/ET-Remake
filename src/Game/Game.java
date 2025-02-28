package Game;

import Engine.Application;

public class Game {

    public static void Start()
    {
        // this is an example, DO NOT use SpriteEntity Directly, SpriteEntity should be inherited from.
        Player example = Application.CreateEntity(new Player());
    }
}
