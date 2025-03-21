package Game;

import Engine.Entities.SpriteEntity;
import Engine.Graphics.SpriteRenderer;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Engine.AssetManagement.AssetManager;

public class Hole extends SpriteEntity {
    private int x;
    private int y;

    public Hole(int x, int y) {
        this.x = x;
        this.y = y;
       
    }

    @Override
    public void Start() {
        GetSpriteRenderer().sprite = AssetManager.LoadSprite("Hole.png");
        transform.position.set(x, y, 0);
        transform.scale = new Vector2f(15, 15);
        tag = "Hole";
        collider.enabled = true;
    }

    @Override
    public void Update() {}
}
