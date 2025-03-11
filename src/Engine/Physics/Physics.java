package Engine.Physics;

import Engine.Application;
import Engine.Entities.Entity;

import java.util.ArrayList;

public class Physics {
    private static ArrayList<Collider> colliders;

    public Physics() {
        colliders = new ArrayList<>();
    }

    // do we need this?
    public static Collider CreateCollider(float radius) {
        Collider newCollider = new Collider(radius);
        colliders.add(newCollider);
        return newCollider;
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

    private static ArrayList<Collision> DetectCollisions() {
        ArrayList<Entity> entities = Application.GetEntities();
        ArrayList<Collision> collisions = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).collider.enabled) {
                continue;
            }
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(j).collider.enabled) {
                    continue;
                }
                Collision collision = DetectCollision(entities.get(i), entities.get(j));
                if (collision.hit) {
                    collisions.add(collision);
                }
            }
        }

        /* DetectCollision through Collider?
        // update array of colliders
        ArrayList<Collider> newColliders = new ArrayList<>();
        for (Entity entity : entities) {
             newColliders.add(entity.collider);

        }
        colliders = newColliders;

        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).enabled) {
                continue;
            }
            for (int j = i + 1; j < colliders.size(); j++) {
                if (colliders.get(j).enabled) {
                    continue;
                }
                Collision collision = Collider.DetectCollision(colliders.get(i), colliders.get(j));
                if (collision.hit) {
                    collisions.add(collision);
                }
            }
        }
        */

        return collisions;
    }

    private static void ResolveCollisions(ArrayList<Collision> collisions) {
        for (Collision collision : collisions) {
            collision.resolve();
        }
    }

    public static void Update() {
        ArrayList<Collision> collisions = DetectCollisions();
        ResolveCollisions(collisions);
    }


}
