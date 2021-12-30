package com.shredquest.zombiegame.application.entities;

import com.shredquest.zombiegame.application.renderer.Mesh;
import org.joml.Vector3f;

public class Entity {
    private String name;
    private Vector3f position;
    private Vector3f rotation;
    private float scale;
    private Mesh mesh;

    public Entity(String name) {
        this.name = name;
        position = new Vector3f(0f, 0f, 0f);
        rotation = new Vector3f(0f, 0f, 0f);
        scale = 1f;
        mesh = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void offsetPosition(Vector3f amount) {
        this.position.x += amount.x;
        this.position.y += amount.y;
        this.position.z += amount.z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void offsetRotation(Vector3f amount) {
        this.rotation.x += amount.x;
        this.rotation.y += amount.y;
        this.rotation.z += amount.z;
    }

    public void face(Vector3f target) {
        Vector3f position = this.getPosition();
        float dx = target.x - position.x;
        float dz = target.z - position.z;
        float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
        float angle = (float) Math.atan(dz / dx);

        this.setRotation(new Vector3f(0f, (float) Math.toDegrees(angle), 0f));
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
