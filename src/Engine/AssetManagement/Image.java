package Engine.AssetManagement;

import Engine.Graphics.Graphics;

public class Image {

    private final String filePath;
    private final int textureID;

    public Image(String filePath) {
        this.filePath = filePath;
        textureID = Graphics.LoadTexture(filePath);
    }

    public String GetFilePath() {
        return filePath;
    }

    public int GetTextureID() {
        return textureID;
    }
}
