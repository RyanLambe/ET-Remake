package Engine.AssetManagement;

import Engine.Graphics.Graphics;

public class Image {

    public String filePath;
    private int textureID;

    public Image(String filePath) {
        this.filePath = filePath;
        textureID = Graphics.LoadTexture(filePath);
    }

    public String getFilePath() {
        return filePath;
    }
}
