package com.shredquest.zombiegame.application.input;

import com.shredquest.zombiegame.application.window.Window;
import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class DevilLevelInput implements Input {
    public static final int KEY_SPACE = 32;
    public static final int KEY_W = 87;
    public static final int KEY_A = 65;
    public static final int KEY_S = 83;
    public static final int KEY_D = 68;
    public static final int KEY_LEFT = GLFW_KEY_LEFT;
    public static final int KEY_RIGHT = GLFW_KEY_RIGHT;

    private long windowId;

    private final Vector2d previousPos;

    private final Vector2d currentPos;

    private final Vector2f displVec;

    private boolean inWindow = false;

    private boolean leftButtonPressed = false;

    private boolean rightButtonPressed = false;

    public DevilLevelInput() {
        windowId = 0;
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
        displVec = new Vector2f();
    }

    @Override
    public void setupCallbacks(Window window) {
        windowId = window.getId();
        glfwSetCursorPosCallback(windowId, (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        glfwSetCursorEnterCallback(windowId, (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetMouseButtonCallback(windowId, (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }

    @Override
    public void detect() {
        displVec.x = 0;
        displVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            boolean rotateX = deltax != 0;
            boolean rotateY = deltay != 0;
            if (rotateX) {
                displVec.y = (float) deltax;
            }
            if (rotateY) {
                displVec.x = (float) deltay;
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    @Override
    public Vector2f getDisplVec() {
        return displVec;
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowId, keyCode) == GLFW_PRESS;
    }

    @Override
    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    @Override
    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }
}
