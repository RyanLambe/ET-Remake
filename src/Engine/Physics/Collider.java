package Engine.Physics;

import Engine.Entities.Entity;

public class Collider {

    public float radius;
    public Boolean enabled;

    public Collider() {
        this.radius = 5;
        enabled = false;
    }

    public static Collision DetectCollision(Entity A, Entity B) {
        Collision collision = new Collision(A, B);
        float a_x = A.transform.position.x;
        float a_y = A.transform.position.y;

        float b_x = B.transform.position.x;
        float b_y = B.transform.position.y;

        double distance = Math.sqrt((b_x - a_x)*(b_x - a_x) + (b_y - a_y)*(b_y - a_y));
        if (distance <= A.collider.radius + B.collider.radius) {
            collision.hit = true;
        }
        return collision;
    }
}
