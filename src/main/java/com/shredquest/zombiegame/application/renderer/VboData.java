package com.shredquest.zombiegame.application.renderer;

public class VboData {

    private final float[] data;

    private final int interval;

    public VboData(float[] data, int interval) {
        this.data = data;
        this.interval = interval;
    }

    public float[] getData() {
        return data;
    }

    public int getInterval() {
        return interval;
    }

}
