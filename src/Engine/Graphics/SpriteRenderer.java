package Engine.Graphics;

import Engine.AssetManagement.Sprite;
import Engine.Entities.Transform;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;

public class SpriteRenderer implements Renderer {

    // Hard coded square shape as every sprite should always have a square mesh
    private static final float[] vertices = {
            -0.5f, -0.5f, 0.0f,
            0.5f,  -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,

            -0.5f, 0.5f, 0.0f,
            0.5f,  -0.5f, 0.0f,
            0.5f,  0.5f, 0.0f
    };
    private static int vao = 0;
    private static int vbo = 0;

    private static Shader spriteShader = null;

    private Vector3f color = new Vector3f(1, 1, 1);

    public Sprite sprite = null;

    public static void PrepRender(){
        if(spriteShader == null)
            return;

        spriteShader.Enable();
        spriteShader.SetUniform("camTransform", Camera.transform.GetInverseMatrix());
        spriteShader.SetUniform("projection", Camera.transform.GetProjectionMatrix());
    }

    public void Render(Transform transform) {
        if(transform == null || spriteShader == null)
            return;

        spriteShader.Enable();
        GL30.glBindVertexArray(vao);

        spriteShader.SetUniform("color", color);
        spriteShader.SetUniform("transform", transform.GetMatrix());

        if(sprite != null){
            spriteShader.SetUniform("useTexture", true);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, sprite.GetNextFrame().GetTextureID());
        }
        else
            spriteShader.SetUniform("useTexture", false);

        // render sprite
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
    }

    public void SetColor(Vector3f color){
        this.color = color;
    }

    public Vector3f GetColor(){
        return color;
    }

    public void Destroy() {

    }

    public static void InitializeStaticValues() {
        // create vertex array object: contains vertex buffer objects
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        // create vertex buffer object: contains data
        vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);

        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices);
        buffer.flip();

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        //Link attributes: tells gpu what the data is
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);

        // create shader
        try {
            spriteShader = new Shader("shaders/SpriteShader.vert", "shaders/SpriteShader.frag");
        } catch(Exception e){
            System.out.println("Error loading sprite shader: " + e.getMessage());
        }
    }

    public static void DestroyStaticValues() {
        spriteShader.Destroy();

        GL15.glDeleteBuffers(vbo);
        GL30.glDeleteVertexArrays(vao);
    }
}
