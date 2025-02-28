package Engine.Graphics;

import Engine.Entities.Transform;

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

    private static int count = 0;

    public SpriteRenderer() {
        if(count == 0){
            InitializeStaticValues();
        }
        count++;
    }

    public void Render(Transform transform) {
        if(transform == null)
            return;

        // enable shader

        // update transform uniform

        // update texture?

        GL30.glBindVertexArray(vao);
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
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

    }

    private void DestroyStaticValues() {
        // destroy shader

        // destroy VAO
        GL15.glDeleteBuffers(vbo);
        GL30.glDeleteVertexArrays(vao);
    }
}
