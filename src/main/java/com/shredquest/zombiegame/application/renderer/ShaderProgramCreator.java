package com.shredquest.zombiegame.application.renderer;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glAttachShader;

public class ShaderProgramCreator {

    public static ShaderProgram create(String vertexShaderCode,
                                       String fragmentShaderCode,
                                       String[] uniformNames) {
        int programId = glCreateProgram();
        compileShader(GL_VERTEX_SHADER, vertexShaderCode, programId);
        compileShader(GL_FRAGMENT_SHADER, fragmentShaderCode, programId);
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            try {
                throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Integer> uniforms = new HashMap<>();

        for (String uniformName : uniformNames) {
            int uniformLocation = glGetUniformLocation(programId,
                    uniformName);
            uniforms.put(uniformName, uniformLocation);
        }

        return new ShaderProgram(programId, uniforms);
    }

    private static void compileShader(int shaderType,
                                      String shaderCode,
                                      int programId) {
        int shaderId = glCreateShader(shaderType);
        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);
        glAttachShader(programId, shaderId);
    }

}
