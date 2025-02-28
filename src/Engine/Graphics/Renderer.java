package Engine.Graphics;

import Engine.Entities.Transform;

public interface Renderer {

    void Render(Transform transform);

    void Destroy();
}
