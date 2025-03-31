package Game.Map;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Engine.AssetManagement.AssetManager;
import Engine.Entities.SpriteEntity;
import Engine.Graphics.SpriteRenderer;
import org.joml.Vector4f;

public class ZoneBackground extends SpriteEntity {
    public String imagePath;

    public ZoneBackground(String imagePath) {
       this.imagePath = imagePath;
    }

    @Override
    public void Start() {
        GetSpriteRenderer().sprite = AssetManager.LoadSprite(imagePath);
        GetSpriteRenderer().SetColor(new Vector4f(1, 1, 1, 1));
        transform.scale = new Vector2f(160, 90);
        transform.position.z = -100;
        
    }

    @Override
    public void Update() {}
}