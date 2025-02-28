package Engine.Entities;

import Engine.Graphics.SpriteRenderer;

public class SpriteEntity extends Entity {

    public void Setup() {
        renderer = new SpriteRenderer();
    }

    public void Start() {
        System.out.println("Error: Function Not Implemented: Entity.Setup");
    }

    public void Update() {
        System.out.println("Error: Function Not Implemented: Entity.Setup");
    }

    public SpriteRenderer GetSpriteRenderer(){
        return (SpriteRenderer)renderer;
    }
}
