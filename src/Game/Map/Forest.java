package Game.Map;

import Engine.Application;
import Engine.Entities.TextEntity;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Forest extends Zone {

    TextEntity tutorial;

    public Forest() {
        super("Forest", "ForestBG.png");
        tutorial = Application.CreateEntity(new TextEntity());
        tutorial.GetTextRenderer().SetText("Press W, A, S, D, or Arrow Keys to Move");
        tutorial.GetTextRenderer().SetColor(new Vector4f(1, 1, 1, 1));
        tutorial.transform.position = new Vector3f(-40, 25, -.1f);
        tutorial.transform.Scale(0.75f);
    }

    @Override
    public void addEntities() {
        // Example: Add trees or NPCs
        System.out.println("Forest zone entities added.");
    }

    @Override
    public void removeEntities() {
        // Example: Remove zone-specific objects when leaving
        System.out.println("Forest zone entities removed.");
        tutorial.Destroy();
    }
}
