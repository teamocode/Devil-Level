package com.shredquest.zombiegame.application.renderer;

import com.shredquest.zombiegame.application.entities.Camera;
import com.shredquest.zombiegame.application.entities.Entity;
import com.shredquest.zombiegame.application.loader.Loader;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class DevilLevelRenderer implements Renderer {
    private  ShaderProgram shaderProgram;
    private Transformation transformation;

    public DevilLevelRenderer() {
        shaderProgram = null;
        transformation = new Transformation();
    }

    @Override
    public void setupShader() {
        shaderProgram =  ShaderProgramCreator.create(
                Loader.loadShader("/shader/vertex.shad"),
                Loader.loadShader("/shader/fragment.shad"),
                new String[] {"texture_sampler", "projection", "modelView"});
    }

    @Override
    public void render(Entity[] renderables, Camera camera) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glUseProgram(shaderProgram.getId());

        Matrix4f projectionMatrix = transformation.getProjectionMatrix((float) Math.toRadians(60.0f),
                1024, 648,
                0.01f, 1000f);
        UniformSetter.setUniform(shaderProgram, "projection", projectionMatrix);

        Matrix4f viewMatrix = transformation.getViewMatrix(camera);

        UniformSetter.setUniform(shaderProgram,"texture_sampler", 0);

        for(Entity renderable : renderables) {

            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(renderable, viewMatrix);
            UniformSetter.setUniform(shaderProgram, "modelView", modelViewMatrix);

            Mesh mesh = renderable.getMesh();

            glBindVertexArray(mesh.getVaoId());

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIboId());

            glEnableVertexAttribArray(0);

            glEnableVertexAttribArray(1);
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, mesh.getTexture().getId());

            glDrawElements(GL_TRIANGLES, mesh.getIndexCount(), GL_UNSIGNED_INT, 0);

            glBindTexture(GL_TEXTURE_2D, 0);
            glDisableVertexAttribArray(1);

            glDisableVertexAttribArray(0);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

            glBindVertexArray(0);
        }

        glUseProgram(0);
    }
}
