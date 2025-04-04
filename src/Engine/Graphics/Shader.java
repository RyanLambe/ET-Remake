package Engine.Graphics;

import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.*;

public class Shader {

    private final int program;

    public Shader(String vertex, String fragment) throws IOException {
        //load vertex shader
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, Files.readString(Paths.get(vertex)));
        GL20.glCompileShader(vertexShader);

        if(GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new IOException("Could not compile vertex shader: " + GL20.glGetShaderInfoLog(vertexShader));
        }

        //load fragment shader
        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, Files.readString(Paths.get(fragment)));
        GL20.glCompileShader(fragmentShader);

        if(GL20.glGetShaderi(fragmentShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new IOException("Could not compile fragment shader: " + GL20.glGetShaderInfoLog(fragmentShader));
        }

        //create shader program
        program = GL20.glCreateProgram();
        GL20.glAttachShader(program, vertexShader);
        GL20.glAttachShader(program, fragmentShader);

        GL20.glLinkProgram(program);
        GL20.glValidateProgram(program);

        GL20.glUseProgram(program);
        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);
    }

    public void Enable(){
        GL20.glUseProgram(program);
    }

    public void Destroy(){
        GL20.glDeleteProgram(program);
    }

    public void SetUniform(String name, boolean value){
        int location = GL20.glGetUniformLocation(program, name);
        GL20.glUniform1i(location, value ? 1 : 0);
    }

    public void SetUniform(String name, float value){
        int location = GL20.glGetUniformLocation(program, name);
        GL20.glUniform1f(location, value);
    }

    public void SetUniform(String name, Vector4f vector){
        int location = GL20.glGetUniformLocation(program, name);
        GL20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
    }

    public void SetUniform(String name, Matrix4f matrix){
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        matrix.get(fb);

        int location = GL20.glGetUniformLocation(program, name);
        GL20.glUniformMatrix4fv(location, false, fb);
    }
}
