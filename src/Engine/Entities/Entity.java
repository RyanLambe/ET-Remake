package Engine.Entities;

import Engine.Application;
import Engine.Graphics.Renderer;

public class Entity {

    public Transform transform = new Transform();
    public Renderer renderer = null;

    public void Setup(){

    }

    public void Start(){

    }

    public void Update(){

    }

    public void Destroy(){
        renderer.Destroy();
        Application.RemoveEntityInternal(this);
    }
}
