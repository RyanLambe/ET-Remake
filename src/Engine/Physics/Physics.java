package Engine.Physics;

import java.util.ArrayList;

public class Physics {
    private static ArrayList<Collider> colliders;

    public Physics() {
        colliders = new ArrayList<>();
    }

    public static Collider CreateCollider() {
        Collider newCollider = new Collider();
        colliders.add(newCollider);
        return newCollider;
    }

    private static ArrayList<Collision> DetectCollisions() {
        ArrayList<Collision> collisions = new ArrayList<>();

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
