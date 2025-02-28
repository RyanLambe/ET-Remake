package Engine.Entities;

import Engine.Application;
import Engine.Graphics.Renderer;

public class Entity {

    public Transform transform = new Transform();
    public Renderer renderer = null;

    public void Setup(){
        System.out.println("Error: Function Not Implemented: Entity.Setup");
    }

    public void Start(){
        System.out.println("Error: Function Not Implemented: Entity.Start");
    }

    public void Update(){
        System.out.println("Error: Function not implemented: Entity.Update");
    }

    public void Destroy(){
        renderer.Destroy();
        Application.RemoveEntityInternal(this);
    }
}
