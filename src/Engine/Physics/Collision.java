package Engine.Physics;
import Engine.Entities.Entity;

public class Collision {
    public Entity entityA;
    public Entity entityB;

    public Boolean hit;

    public Collision(Entity entityA, Entity entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
        hit = false;
    }

    public void resolve() {
        entityA.OnCollision(entityB);
        hit = false;
    }
}
