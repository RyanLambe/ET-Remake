package Engine;

import Engine.Graphics.Graphics;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

// singleton
public class Window {

    private static boolean created = false;
    private static long window;
    private static int aspectRatioX;
    private static int aspectRatioY;

    public static void Create(String title, int defaultWidth, int defaultHeight) {
        if(created)
            return;

        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create window
        window = glfwCreateWindow(defaultWidth, defaultHeight, title, NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        int gcd = GreatestCommonDenominator(defaultWidth, defaultHeight);
        aspectRatioX = defaultWidth / gcd;
        aspectRatioY = defaultHeight / gcd;

        glfwSetWindowAspectRatio(window, aspectRatioX, aspectRatioY);
        glfwSetWindowSizeCallback(window, (window, width, height) -> Graphics.UpdateFramebufferSize(width, height));

        glfwSetKeyCallback(window, (long window, int key, int scancode, int action, int mods) -> Input.KeyCallback(key));

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // Enable v-sync
        glfwShowWindow(window);

        created = true;
    }

    public static boolean ShouldClose() {
        return glfwWindowShouldClose(window);
    }

    public static void Update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public static boolean GetKey(int key) {
        if(!created)
            throw new IllegalStateException("Window is not created, Cannot Get Key.");
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    public static float GetAspectRatioX() {
        return aspectRatioX;
    }

    public static float GetAspectRatioY() {
        return aspectRatioY;
    }

    public static void Destroy()
    {
        // destroy window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

        created = false;
    }

    private static int GreatestCommonDenominator(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
