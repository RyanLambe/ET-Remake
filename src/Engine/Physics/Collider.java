package Engine.Physics;

import Engine.Entities.Entity;
import org.joml.Vector2f;

public class Collider {

    private ColliderType type = ColliderType.Circle;
    private float radius = 5;
    private Vector2f size = new Vector2f(10, 10);

    public Boolean enabled = false;

    public void SetRadius(float radius){
        this.radius = radius / 10;
        type = ColliderType.Circle;
    }

    public void SetRectSize(float x, float y){
        this.size = new Vector2f(x / 10, y / 10);
        type = ColliderType.Rectangle;
    }

    public ColliderType getType() {
        return type;
    }

    public static Collision DetectCollision(Entity A, Entity B) {
        if (A.collider.getType() == ColliderType.Circle) {
            if (B.collider.getType() == ColliderType.Circle) {
                return CircleCircleCollision(A, B);
            }
            else {
                Collision temp = RectCircleCollision(B, A);
                temp.entityA = A;
                temp.entityB = B;
                return temp;
            }
        }
        else {
            if (B.collider.getType() == ColliderType.Circle) {
                return RectCircleCollision(A, B);
            }
            else {
                return RectRectCollision(A, B);
            }
        }

    }

    private static Collision CircleCircleCollision(Entity A, Entity B) {
        Collision collision = new Collision(A, B);

        float a_x = A.transform.position.x;
        float a_y = A.transform.position.y;

        float b_x = B.transform.position.x;
        float b_y = B.transform.position.y;

        double distance = Math.sqrt(((b_x - a_x)*(b_x - a_x)) + ((b_y - a_y)*(b_y - a_y)));
        if (distance <= A.collider.radius + B.collider.radius) {
            collision.hit = true;
        }
        return collision;
    }

    private static Collision RectRectCollision(Entity A, Entity B) {
        Collision collision = new Collision(A, B);

        //check x axis
        float ax = A.transform.position.x;
        float axs = (A.collider.size.x / 2);
        float bx = B.transform.position.x;
        float bxs = (B.collider.size.x / 2);

        if (bx - bxs > ax + axs) return collision;
        if (bx + bxs < ax - axs) return collision;

        //check y axis
        float ay = A.transform.position.y;
        float ays = (A.collider.size.y / 2);
        float by = B.transform.position.y;
        float bys = (B.collider.size.y / 2);

        if (by - bys > ay + ays) return collision;
        if (by + bys < ay - ays) return collision;

        //return collision data
        collision.hit = true;
        return collision;
    }

    private static Collision RectCircleCollision(Entity rect, Entity circle) {
        Collision collision = new Collision(rect, circle);

        //get positions of rect and circle
        float ax = rect.transform.position.x;
        float axs = (rect.collider.size.x / 2);
        float ay = rect.transform.position.y;
        float ays = (rect.collider.size.y / 2);

        float bx = circle.transform.position.x;
        float by = circle.transform.position.y;

        //get distance between objects
        float distanceX = Math.max(ax - axs, Math.min(bx, ax + axs)) - bx;
        float distanceY = Math.max(ay - ays, Math.min(by, ay + ays)) - by;

        double distance = Math.max(Math.sqrt((distanceX * distanceX) + (distanceY * distanceY)), 0.000001);

        //check if collision
        if (distance <= circle.collider.radius) {
            collision.hit = true;
        }
        return collision;
    }
}
