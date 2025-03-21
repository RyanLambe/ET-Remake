package Engine.Entities;

import Engine.Application;
import Engine.Graphics.Renderer;
import Engine.Physics.Collider;

public class Entity {

    public Transform transform = new Transform();
    public Collider collider = new Collider();
    public Renderer renderer = null;
    public String tag = "default";

    public void Setup(){

    }

    public void Start(){

    }

    public void Update(){

    }

    public void OnCollision(Entity other) {

    }

    public void Destroy(){
        renderer.Destroy();
        Application.RemoveEntityInternal(this);
    }
}
