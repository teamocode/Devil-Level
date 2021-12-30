package com.shredquest.zombiegame.application.entities;

import com.shredquest.zombiegame.application.input.DevilLevelInput;
import com.shredquest.zombiegame.application.input.Input;
import com.shredquest.zombiegame.application.loader.Loader;
import com.shredquest.zombiegame.application.renderer.Mesh;
import com.shredquest.zombiegame.application.renderer.MeshCreator;
import com.shredquest.zombiegame.application.renderer.Texture;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class Player extends Entity {
    private static final float PLAYER_SPEED = 0.2f;
    private static final float PLAYER_ROT_SPEED = 3f;
    private static final float GUN_COOLDOWN_TIME = 5f;
    private float health;
    private boolean isShooting;
    private boolean isGunCoolingDown;
    private long lastShotTime;

    public Player() {
        super("player");
        isGunCoolingDown = false;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void affectHealth(float amount) {
        health += amount;
    }

    public void handle(Input input) {
        isShooting = false;
        if(isGunCoolingDown) {
            long currentTime = System.nanoTime() / 100000000;
            if(currentTime - lastShotTime > GUN_COOLDOWN_TIME) {
                isGunCoolingDown = false;
            }
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_W)) {
            this.getPosition().x += (float)Math.sin(Math.toRadians(this.getRotation().y)) * PLAYER_SPEED;
            this.getPosition().z += (float)Math.cos(Math.toRadians(this.getRotation().y)) * -PLAYER_SPEED;
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_A)) {
            this.getPosition().x += (float)Math.sin(Math.toRadians(this.getRotation().y - 90f)) * PLAYER_SPEED;
            this.getPosition().z += (float)Math.cos(Math.toRadians(this.getRotation().y - 90f)) * -PLAYER_SPEED;
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_S)) {
            this.getPosition().x += (float)Math.sin(Math.toRadians(this.getRotation().y)) * -PLAYER_SPEED;
            this.getPosition().z += (float)Math.cos(Math.toRadians(this.getRotation().y)) * PLAYER_SPEED;
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_D)) {
            this.getPosition().x += (float)Math.sin(Math.toRadians(this.getRotation().y + 90f)) * PLAYER_SPEED;
            this.getPosition().z += (float)Math.cos(Math.toRadians(this.getRotation().y + 90f)) * -PLAYER_SPEED;
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_LEFT)) {
            this.offsetRotation(new Vector3f(0f, -PLAYER_ROT_SPEED, 0f));
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_RIGHT)) {
            this.offsetRotation(new Vector3f(0f, PLAYER_ROT_SPEED, 0f));
        }
        if(input.isKeyPressed(DevilLevelInput.KEY_SPACE) && !isGunCoolingDown) {
            isShooting = true;
            isGunCoolingDown = true;
            lastShotTime = System.nanoTime()/ 100000000;
        }
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void shoot(List<Bullet> bullets) {
        Bullet bullet = new Bullet("bullet", new Vector3f(this.getPosition()), new Vector3f(this.getRotation()));
        Texture texture = Loader.loadTexture("/texture/questblock.png");
        Mesh cuboid = MeshCreator.createCuboid(0.5f, 0.5f, 0.5f, texture);
        bullet.setMesh(cuboid);
        bullets.add(bullet);
    }
}
