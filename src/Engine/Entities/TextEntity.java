package Engine.Entities;

import Engine.Graphics.TextRenderer;

public class TextEntity extends Entity{
    public void Setup() {
        this.renderer = new TextRenderer();
    }

    public TextRenderer GetTextRenderer(){
        return (TextRenderer) renderer;
    }
}
