package Game;

import Engine.Entities.SpriteEntity;
import Engine.Graphics.SpriteRenderer;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Engine.AssetManagement.AssetManager;

public class Hole extends SpriteEntity {
    private int x;
    private int y;

    public boolean hasPhone = false;

    public Hole(int x, int y, boolean hasPhone) {
        this.x = x;
        this.y = y;
        this.hasPhone = hasPhone;
    }

    @Override
    public void Start() {
        GetSpriteRenderer().sprite = AssetManager.LoadSprite("Hole.png");
        transform.position.set(x, y, 0);
        transform.scale = new Vector2f(10, 10);
        tag = "Hole";
        collider.enabled = true;

        collider.SetRadius(5);
        //transform.Scale(0.);
    }

    @Override
    public void Update() {

    }
}
