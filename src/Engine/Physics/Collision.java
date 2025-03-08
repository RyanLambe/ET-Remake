package Engine.Physics;
import Engine.Entities.Entity;

public class Collision {
    public Entity entityA;
    public Entity entityB;

    public Collision(Entity entityA, Entity entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
    }

    public void resolve() {

    }
}
