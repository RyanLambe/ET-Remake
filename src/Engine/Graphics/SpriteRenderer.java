package Engine.Graphics;

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

    private static Shader universalShader = null;

    private static int count = 0;

    private Vector3f color;

    public SpriteRenderer() {
        if(count == 0){
            InitializeStaticValues();
        }
        count++;
    }

    public void Render(Transform transform) {
        if(transform == null || universalShader == null)
            return;

        universalShader.Enable();

        // update transform uniform

        // update texture?
        universalShader.SetUniform("transform", transform.getMatrix());

        // render sprite
        GL30.glBindVertexArray(vao);
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void SetColor(Vector3f color){
        universalShader.SetUniform("color", color);
        this.color = color;
    }

    public Vector3f GetColor(){
        return color;
    }

    public void Destroy() {
        count--;
        if(count == 0){
            DestroyStaticValues();
        }
    }

    private void InitializeStaticValues() {
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
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);

        // create shader
        try {
            universalShader = new Shader("shaders/UniversalShader.vert", "shaders/UniversalShader.frag");
        } catch(Exception e){
            System.out.println("Error loading universal shader: " + e.getMessage());
        }
    }

    private void DestroyStaticValues() {
        universalShader.Destroy();

        GL15.glDeleteBuffers(vbo);
        GL30.glDeleteVertexArrays(vao);
    }
}
