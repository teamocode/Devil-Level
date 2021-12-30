package com.shredquest.zombiegame.application.input;

import com.shredquest.zombiegame.application.window.Window;
import org.joml.Vector2f;

public interface Input {
    void setupCallbacks(Window window);
    Vector2f getDisplVec();
    void detect();
    boolean isKeyPressed(int keyCode);
    boolean isLeftButtonPressed();
    boolean isRightButtonPressed();
}
