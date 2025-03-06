package Engine.Graphics;

import Engine.Application;
import Engine.Entities.Entity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Graphics {

    private static int fps;

    public static void Start(){
        // setup OpenGL
        GL.createCapabilities();

        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public static void Render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        SpriteRenderer.PrepRender();

        ArrayList<Entity> entities = Application.GetEntities();
        for(Entity entity : entities){
            if(entity.renderer != null)
                entity.renderer.Render(entity.transform);
        }
    }

    public static void SetFPS(int newFps) {
        fps = newFps;
    }

    public static int GetFPS() {
        return fps;
    }

    public static void UpdateFramebufferSize(int width, int height) {
        GL11.glViewport(0, 0, width, height);
    }

}
