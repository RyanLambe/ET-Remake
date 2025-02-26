package Engine.Graphics;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

    public static void Start(){
        // setup OpenGL
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public static void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


    }
}
