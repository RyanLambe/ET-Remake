package Engine.Entities;

import Engine.Graphics.Renderer;

public interface Entity {

    Transform transform = new Transform();
    Renderer renderer = null;

    void Setup();
    void Start();
    void Update();
}
