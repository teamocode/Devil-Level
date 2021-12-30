package com.shredquest.zombiegame.application.entities;

import com.shredquest.zombiegame.application.renderer.Mesh;
import com.shredquest.zombiegame.application.renderer.Renderable;

import java.util.List;

public interface EntityManager {
    void setupInitEntities();
    Camera getCamera();
    Level getLevel();
    Player getPlayer();
    List<ZombieSpawner> getZmbSpwnrs();
    List<Zombie> getZombies();
    List<Bullet> getBullets();
    Entity[] getRenderables();
    Mesh getZombieMesh();
}
