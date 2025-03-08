package Engine.Entities;

import Engine.Graphics.Camera;
import Engine.Window;
import org.joml.*;

public class Transform {

    public Vector3f position = new Vector3f(0);
    public float rotation = 0;
    public Vector2f scale = new Vector2f(10);

    public void Translate(float x, float y, float z){
        Vector2f xAxis = new Vector2f(1, 0);
        Vector2f yAxis = new Vector2f(0, 1);

        Matrix2f rotationMatrix = new Matrix2f().rotate(rotation);
        xAxis.mul(rotationMatrix);
        yAxis.mul(rotationMatrix);

        xAxis.mul(x);
        yAxis.mul(y);

        position.add(new Vector3f(xAxis, 0));
        position.add(new Vector3f(yAxis, 0));
        position.z += z;
    }

    public void Rotate(float x){
        rotation += x;
    }

    public void Scale(float x, float y){
        scale.x *= x;
        scale.y *= y;
    }

    public void Scale(float multiplier){
        scale.x *= multiplier;
        scale.y *= multiplier;
    }

    public Matrix4f GetMatrix(){
        return new Matrix4f()
                .translate(position.x, position.y, position.z / Camera.farPlane)
                .rotate(rotation, 0, 0, 1)
                .scale(scale.x, scale.y, 1);
    }

    public Matrix4f GetInverseMatrix() {
        return new Matrix4f()
                .translate(-position.x, -position.y, -position.z)
                .rotate(-rotation, 0, 0, 1);
    }

    public Matrix4f GetProjectionMatrix() {
        return new Matrix4f().ortho2D(
                -5 * Window.GetAspectRatioX(), 5 * Window.GetAspectRatioX(),
                -5 * Window.GetAspectRatioY(), 5 * Window.GetAspectRatioY());
    }

    public static Vector2f PercentageToUnits(float x, float y){
        return new Vector2f(10 * x * Window.GetAspectRatioX(), 10 * y * Window.GetAspectRatioY());
    }

    public static Vector3f PercentageToUnits(float x, float y, float z){
        return new Vector3f(10 * x * Window.GetAspectRatioX(),  10 * y * Window.GetAspectRatioY(), z);
    }
}
