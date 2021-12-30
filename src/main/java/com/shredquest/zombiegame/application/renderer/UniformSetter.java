package com.shredquest.zombiegame.application.renderer;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class UniformSetter {
    public static void setUniform(ShaderProgram shaderProgram, String uniformName, int value) {
        glUniform1i(shaderProgram.getUniforms().get(uniformName), value);
    }

    public static void setUniform(ShaderProgram shaderProgram, String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(shaderProgram.getUniforms().get(uniformName), false, fb);
        }
    }
}
