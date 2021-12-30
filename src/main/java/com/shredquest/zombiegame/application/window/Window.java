package com.shredquest.zombiegame.application.window;

public interface Window {
    void create();
    long getId();
    void update();
    boolean shouldClose();
    void destroy();
}
