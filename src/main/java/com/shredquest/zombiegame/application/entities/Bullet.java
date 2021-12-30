package com.shredquest.zombiegame.application.entities;

import org.joml.Vector3f;

public class Bullet extends Entity {

    private static final float BULLET_STEP = 1f;

    private Vector3f initPosition;

    public Bullet(String name, Vector3f initPosition, Vector3f initRotation) {
        super(name);
        this.initPosition = initPosition;
        this.setPosition(new Vector3f(initPosition));
        this.setRotation(initRotation);
    }

    public void move() {
        this.getPosition().x += (float)Math.sin(Math.toRadians(this.getRotation().y)) * BULLET_STEP;
        this.getPosition().z += (float)Math.cos(Math.toRadians(this.getRotation().y)) * -BULLET_STEP;
    }

    public Vector3f getInitPosition() {
        return initPosition;
    }
}
