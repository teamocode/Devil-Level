package com.shredquest.zombiegame.application.entities;

import com.shredquest.zombiegame.application.loader.Loader;
import com.shredquest.zombiegame.application.renderer.Mesh;
import com.shredquest.zombiegame.application.renderer.MeshCreator;
import com.shredquest.zombiegame.application.renderer.Texture;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class DevilLevelEntMngr implements EntityManager {
    private final Camera camera;
    private final Level level;
    private final Player player;
    private final ZombieSpawner spawnerFront;
    private final ZombieSpawner spawnerBack;
    private final ZombieSpawner spawnerLeft;
    private final ZombieSpawner spawnerRight;
    private final List<Zombie> zombies;
    private final List<Bullet> bullets;
    private Texture texture;
    private Mesh zombieMesh;

    public DevilLevelEntMngr() {
        camera = new Camera();
        level = new Level();
        player = new Player();
        spawnerFront = new ZombieSpawner("front");
        spawnerBack = new ZombieSpawner("back");
        spawnerLeft = new ZombieSpawner("left");
        spawnerRight = new ZombieSpawner("right");
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    @Override
    public void setupInitEntities() {
        texture = Loader.loadTexture("/texture/questblock.png");
        zombieMesh = MeshCreator.createCuboid(1f, 2f, 1f, texture);
        level.setMesh(MeshCreator.createCuboid(80f, 1f, 80f, texture));
        level.setPosition(new Vector3f(0f, -1.5f, 0f));
        player.setMesh(MeshCreator.createCuboid(1f, 1f, 1f, texture));
        player.setHealth(100f);
        spawnerFront.setPosition(new Vector3f(0f, 0f, -50f));
        spawnerBack.setPosition(new Vector3f(0f, 0f, 50f));
        spawnerLeft.setPosition(new Vector3f(-50f, 0f, 0f));
        spawnerRight.setPosition(new Vector3f(50f, 0f, 0f));
        camera.setPosition(new Vector3f(0f, 6f, 10f));
        camera.setRotation(new Vector3f(20f, 0f, 0f));
    }

    @Override
    public Camera getCamera() {
        return camera;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<ZombieSpawner> getZmbSpwnrs() {
        List<ZombieSpawner> zombieSpawners = new ArrayList<>();
        zombieSpawners.add(spawnerFront);
        zombieSpawners.add(spawnerBack);
        zombieSpawners.add(spawnerLeft);
        zombieSpawners.add(spawnerRight);
        return zombieSpawners;
    }

    @Override
    public List<Zombie> getZombies() {
        return zombies;
    }

    @Override
    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public Entity[] getRenderables() {
        List<Entity> renderableList = new ArrayList<>();
        renderableList.add(level);
        renderableList.add(player);
        for(Bullet bullet : bullets) {
            renderableList.add(bullet);
        }
        for(Zombie zombie : zombies) {
            renderableList.add(zombie);
        }
        Entity[] renderables = new Entity[renderableList.size()];
        return renderableList.toArray(renderables);
    }

    @Override
    public Mesh getZombieMesh() {
        return zombieMesh;
    }
}
