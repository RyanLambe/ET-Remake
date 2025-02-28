package Engine.Entities;

import Engine.Graphics.SpriteRenderer;

public class SpriteEntity extends Entity {

    public void Setup() {
        renderer = new SpriteRenderer();
    }

    public void Start() {
        System.out.println("Error: Function Not Implemented: SpriteEntity.Start");
    }

    public void Update() {
        System.out.println("Error: Function Not Implemented: SpriteEntity.Update");
    }

    public SpriteRenderer GetSpriteRenderer(){
        return (SpriteRenderer)renderer;
    }
}
