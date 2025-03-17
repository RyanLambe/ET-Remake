package Game;

import Engine.Application;
import Engine.Entities.TextEntity;
import Engine.Entities.Transform;

public class Game {

    public static void Start()
    {

        // this is an example, DO NOT use SpriteEntity Directly, SpriteEntity should be inherited from.


        TextEntity text = Application.CreateEntity(new TextEntity());
        text.transform.scale = Transform.PercentageToUnits(0.005f, 0.005f);


        TextEntity text2 = Application.CreateEntity(new TextEntity());
        text2.GetTextRenderer().SetText("How's it going?");
        text2.transform.scale = Transform.PercentageToUnits(0.005f, 0.005f);
        text2.transform.position.y -= 20;
        text2.transform.position.x -= 50;

        Player example = Application.CreateEntity(new Player());

    }
}
