package Engine.Physics;

import Engine.Application;
import Engine.Entities.Entity;

import java.util.ArrayList;

public class Physics {
    private static ArrayList<Collider> colliders = new ArrayList<>();

    public Physics() {

    }

    public static Collider CreateCollider() {
        Collider newCollider = new Collider();
        colliders.add(newCollider);
        return newCollider;
    }

    private static ArrayList<Collision> DetectCollisions() {
        ArrayList<Entity> entities = Application.GetEntities();
        ArrayList<Collision> collisions = new ArrayList<>();

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
                Collision collision = Collider.DetectCollision(entities.get(i), entities.get(j));
                if (collision.hit) {
                    collisions.add(collision);
                }
            }
        }

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
