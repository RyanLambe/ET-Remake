package Game;

import Engine.Clock;
import Engine.Entities.Entity;
import Engine.Entities.SpriteEntity;
import Engine.Input;
import org.joml.Vector3f;

// currently used for engine testing
public class Player extends SpriteEntity {

    @Override
    public void Start() {
        // super.Start() is Unnecessary
        GetSpriteRenderer().SetColor(new Vector3f(0, 1, 0));
    }

    @Override
    public void Update() {
        // super.Update() is Unnecessary
        if(Input.GetKey('W'))
            transform.Translate(0, Clock.DeltaTime(), 0);
        if(Input.GetKey('S'))
            transform.Translate(0, -Clock.DeltaTime(), 0);
        if(Input.GetKey('D'))
            transform.Translate(Clock.DeltaTime(), 0, 0);
        if(Input.GetKey('A'))
            transform.Translate(-Clock.DeltaTime(), 0, 0);

        if(Input.GetKey('E'))
            transform.Rotate(Clock.DeltaTime());
        if(Input.GetKey('Q'))
            transform.Rotate(-Clock.DeltaTime());

        if(Input.GetKey('R'))
            transform.Scale(1 + Clock.DeltaTime());
        if(Input.GetKey('F'))
            transform.Scale(1 - Clock.DeltaTime());
    }

    @Override
    public void OnCollision(Entity other) {
        // super.OnCollision() is Unnecessary
    }
}
