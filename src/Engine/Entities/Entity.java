package Engine.Entities;

import Engine.Graphics.Renderer;

public class Entity {

    Transform transform = new Transform();
    Renderer renderer = null;

    public void Setup(){
        System.out.println("Error: Function Not Implemented: Entity.Setup");
    }

    public void Start(){
        System.out.println("Error: Function Not Implemented: Entity.Start");
    }

    public void Update(){
        System.out.println("Error: Function not implemented: Entity.Update");
    }
}
