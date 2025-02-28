package Game;

import Engine.Application;
import Engine.Entities.SpriteEntity;
import org.joml.Vector3f;

public class Game {

    public static void Start()
    {
        // this is an example, DO NOT use SpriteEntity Directly, SpriteEntity should be inherited from.
        SpriteEntity test = Application.CreateEntity(new SpriteEntity());
        test.GetSpriteRenderer().SetColor(new Vector3f(0, 1, 0));
    }
}
