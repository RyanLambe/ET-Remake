package Game.Map;

import Engine.Entities.SpriteEntity;
import Engine.Graphics.SpriteRenderer;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Engine.AssetManagement.AssetManager;
import org.joml.Vector4f;

public class WhitehouseFG extends SpriteEntity {
   

    public WhitehouseFG(int x, int y) {

    }

    @Override
    public void Start() {
        GetSpriteRenderer().sprite = AssetManager.LoadSprite("WhitehouseFG.png");
        GetSpriteRenderer().SetColor(new Vector4f(1, 1, 1, 1));
        transform.scale = new Vector2f(160, 90);
        transform.position.z = 89;
    }

    @Override
    public void Update() {}
    

}
