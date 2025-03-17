package Engine.Graphics;

import Engine.Entities.Transform;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBEasyFont;

import java.nio.ByteBuffer;


public class TextRenderer implements Renderer {

    private static Shader textShader;

    private String text = "Hello World!";
    private Vector3f color = new Vector3f(1, 1, 1);

    private int vao = 0;
    private int vbo = 0;
    private int quads = 0;

    public TextRenderer() {
        GenerateText();
    }

    public static void PrepRender(){
        if(textShader == null)
            return;

        textShader.Enable();
        textShader.SetUniform("camTransform", Camera.transform.GetInverseMatrix());
        textShader.SetUniform("projection", Camera.transform.GetProjectionMatrix());
    }

    @Override
    public void Render(Transform transform) {

        textShader.Enable();
        GL30.glBindVertexArray(vao);

        textShader.SetUniform("color", color);
        textShader.SetUniform("transform", transform.GetMatrix());

        GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
    }

    public void SetText(String text){
        this.text = text;
        GenerateText();
    }

    public void SetColor(Vector3f color){
        this.color = color;
    }

    public static void InitializeStaticValues(){
        try {
            textShader = new Shader("shaders/TextShader.vert", "shaders/TextShader.frag");
        } catch(Exception e){
            System.out.println("Error loading text shader: " + e.getMessage());
        }
    }

    private void GenerateText(){

        // create text buffer
        ByteBuffer charBuffer = BufferUtils.createByteBuffer(text.length() * 270);
        quads = STBEasyFont.stb_easy_font_print(-0.5f, -0.5f, text, null, charBuffer);

        // create VAO
        if(vao == 0)
            vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        // create vertex buffer object: contains data
        if(vbo != 0)
            GL15.glDeleteBuffers(vbo);
        vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, charBuffer, GL15.GL_STATIC_DRAW);

        //Link attributes: tells gpu what the data is
        GL20.glVertexAttribPointer(0, 4, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
    }

    @Override
    public void Destroy() {
        GL15.glDeleteBuffers(vbo);
        GL30.glDeleteVertexArrays(vao);
    }

    public static void DestroyStaticValues() {
        textShader.Destroy();
    }
}
