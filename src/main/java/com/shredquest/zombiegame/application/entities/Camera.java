package com.shredquest.zombiegame.application.entities;

import org.joml.Vector3f;

public class Camera extends Entity {
    public Camera() {
        super("camera");
    }

    public void anchorTo(Entity target) {
        Vector3f targetPosition = new Vector3f(target.getPosition().x, 0.75f, target.getPosition().z);
        this.setPosition(targetPosition);
        this.setRotation(target.getRotation());
    }
}
