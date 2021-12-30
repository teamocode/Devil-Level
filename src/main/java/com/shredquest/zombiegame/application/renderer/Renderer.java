package com.shredquest.zombiegame.application.renderer;

import com.shredquest.zombiegame.application.entities.Camera;
import com.shredquest.zombiegame.application.entities.Entity;

public interface Renderer {
    void setupShader();
    void render(Entity[] renderables, Camera camera);
}
