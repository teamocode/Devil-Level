package com.shredquest.zombiegame.application.window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DevilLevelWindow implements Window {
    private long id;
    private int width;
    private int height;
    private String title;

    public DevilLevelWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        id = 0;
    }

    @Override
    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);


        id = glfwCreateWindow(width, height, title, NULL, NULL);

        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }


        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(
                id,
                (vidMode.width() - width) / 2,
                (vidMode.height() - height) / 2
        );

        glfwMakeContextCurrent(id);

        glfwSwapInterval(1);

        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void update() {
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(id);
    }

    @Override
    public void destroy() {
        glfwFreeCallbacks(id);
        glfwDestroyWindow(id);
        glfwTerminate();
    }
}
