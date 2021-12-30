package com.shredquest.zombiegame.application.renderer;

import org.joml.Vector3f;

import java.util.List;

public class Mesh {
    private int vaoId;
    private List<Integer> vboIds;
    private int iboId;
    private int indexCount;
    private Vector3f colour;
    private Texture texture;

    public Mesh(int vaoId, List<Integer> vboIds, int iboId, int indexCount, Texture texture) {
        this.vaoId = vaoId;
        this.vboIds = vboIds;
        this.iboId = iboId;
        this.indexCount = indexCount;
        colour = new Vector3f(0f, 0f, 0f);
        this.texture = texture;
    }

    public int getVaoId() {
        return vaoId;
    }

    public List<Integer> getVboIds() {
        return vboIds;
    }

    public int getIboId() {
        return iboId;
    }

    public int getIndexCount() {
        return indexCount;
    }

    public Texture getTexture() {
        return texture;
    }

}
