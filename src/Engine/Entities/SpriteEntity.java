package Engine.Entities;

import Engine.Graphics.SpriteRenderer;

public class SpriteEntity extends Entity {

    public void Setup() {
        this.renderer = new SpriteRenderer();
    }

    public SpriteRenderer GetSpriteRenderer(){
        return (SpriteRenderer)renderer;
    }
}
