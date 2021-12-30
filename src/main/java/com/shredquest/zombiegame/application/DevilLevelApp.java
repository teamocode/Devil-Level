package com.shredquest.zombiegame.application;

import com.shredquest.zombiegame.application.entities.DevilLevelEntMngr;
import com.shredquest.zombiegame.application.entities.EntityManager;
import com.shredquest.zombiegame.application.input.DevilLevelInput;
import com.shredquest.zombiegame.application.input.Input;
import com.shredquest.zombiegame.application.logic.DevilLevelLogic;
import com.shredquest.zombiegame.application.logic.Logic;
import com.shredquest.zombiegame.application.renderer.DevilLevelRenderer;
import com.shredquest.zombiegame.application.renderer.Renderer;
import com.shredquest.zombiegame.application.window.DevilLevelWindow;
import com.shredquest.zombiegame.application.window.Window;

public class DevilLevelApp implements Application {
    public static final int WINDOW_WIDTH = 2000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final String WINDOW_TITLE = "Devil Level";
    private final Window window;
    private final EntityManager entityManager;
    private final Input input;
    private final Logic logic;
    private final Renderer renderer;

    public DevilLevelApp() {
        window = new DevilLevelWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE);
        input = new DevilLevelInput();
        entityManager = new DevilLevelEntMngr();
        logic = new DevilLevelLogic();
        renderer = new DevilLevelRenderer();
    }

    @Override
    public void run() {
        create();
        update();
        destroy();
    }

    private void create() {
        window.create();
        input.setupCallbacks(window);
        entityManager.setupInitEntities();
        renderer.setupShader();
    }

    private void update() {
        while(!window.shouldClose()) {
            input.detect();
            logic.govern(entityManager, input);
            renderer.render(entityManager.getRenderables(), entityManager.getCamera());
            window.update();
        }
    }

    private void destroy() {
        window.destroy();
    }
}
