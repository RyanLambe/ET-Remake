package Engine.Graphics;

import Engine.Application;
import Engine.Entities.Entity;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

public class Graphics {

    private static float defaultFps = 1;

    public static void Start(){
        // setup OpenGL
        GL.createCapabilities();

        GL11.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_STENCIL_TEST);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        SpriteRenderer.InitializeStaticValues();
        TextRenderer.InitializeStaticValues();
    }

    public static void Render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);

        SpriteRenderer.PrepRender();
        TextRenderer.PrepRender();

        ArrayList<Entity> entities = Application.GetEntities();
        for(Entity entity : entities){
            if(entity.renderer != null)
                entity.renderer.Render(entity.transform);
        }
    }

    public static void SetDefaultFPS(float newFps) {
        defaultFps = newFps;
    }

    public static float GetDefaultFPS() {
        return defaultFps;
    }

    public static void UpdateFramebufferSize(int width, int height) {
        GL11.glViewport(0, 0, width, height);
    }

    public static int LoadTexture(String filePath) {
        int id = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = STBImage.stbi_load("./Assets/" + filePath, width, height, comp, 4);

        if (data != null) {
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width.get(), height.get(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            STBImage.stbi_image_free(data);
            return id;
        }

        System.out.println("Failed to load texture: " + filePath + ".\n");
        return 0;
    }

    public static void Destroy() {
        SpriteRenderer.DestroyStaticValues();
        TextRenderer.DestroyStaticValues();
    }
}
