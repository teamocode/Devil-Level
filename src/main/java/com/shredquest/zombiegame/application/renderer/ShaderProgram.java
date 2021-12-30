package com.shredquest.zombiegame.application.renderer;

import java.util.Map;

public class ShaderProgram {

    private int id;

    private Map<String, Integer> uniforms;

    public ShaderProgram(int id, Map<String, Integer> uniforms) {
        this.id = id;
        this.uniforms = uniforms;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getUniforms() {
        return uniforms;
    }
}
