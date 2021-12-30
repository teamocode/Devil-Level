package com.shredquest.zombiegame.application.renderer;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class MeshCreator {

    public static Mesh createCuboid(float width, float height, float length, Texture texture) {
        int vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);
        float[] vertices = {
                0f - width / 2f, 0f + height / 2f, 0f + length / 2f,
                0f - width / 2f, 0f - height / 2f, 0f + length / 2f,
                0f + width / 2f, 0f - height / 2f, 0f + length / 2f,
                0f + width / 2f, 0f + height / 2f, 0f + length / 2f,

                0f - width / 2f, 0f + height / 2f, 0f - length / 2f,
                0f + width / 2f, 0f + height / 2f, 0f - length / 2f,
                0f - width / 2f, 0f - height / 2f, 0f - length / 2f,
                0f + width / 2f, 0f - height / 2f, 0f - length / 2f,

                // For text coords in top face
                0f - width / 2f, 0f + height / 2f, 0f - length / 2f,
                0f + width / 2f, 0f + height / 2f, 0f - length / 2f,
                0f - width / 2f, 0f + height / 2f, 0f + length / 2f,
                0f + width / 2f, 0f + height / 2f, 0f + length / 2f,

                // For text coords in right face
                0f + width / 2f, 0f + height / 2f, 0f + length / 2f,
                0f + width / 2f, 0f - height / 2f, 0f + length / 2f,

                // For text coords in left face
                0f - width / 2f, 0f + height / 2f, 0f + length / 2f,
                0f - width / 2f, 0f - height / 2f, 0f + length / 2f,

                // For text coords in bottom face
                0f - width / 2f, 0f - height / 2f, 0f - length / 2f,
                0f + width / 2f, 0f - height / 2f, 0f - length / 2f,
                0f - width / 2f, 0f - height / 2f, 0f + length / 2f,
                0f + width / 2f, 0f - height / 2f, 0f + length / 2f
        };
        VboData vertexData = new VboData(vertices, 3);
        float[] textureCoordinates = {
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,

                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,

                // For text coords in top face
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,
                // For text coords in right face
                0.0f, 0.0f,
                0.0f, 0.5f,
                // For text coords in left face
                0.5f, 0.0f,
                0.5f, 0.5f,
                // For text coords in bottom face
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f,
        };
        VboData textureData = new VboData(textureCoordinates, 2);
        int[] indices = {
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                8, 10, 11, 9, 8, 11,
                // Right face
                12, 13, 7, 5, 12, 7,
                // Left face
                14, 15, 6, 4, 14, 6,
                // Bottom face
                16, 18, 19, 17, 16, 19,
                // Back face
                //7, 6, 4, 7, 4, 5
                4, 6, 7, 5, 4, 7
        };

        VboData[] data = {vertexData, textureData};

        List<Integer> vboIds = createVbos(data);

        int iboId = createIbo(indices);

        glBindVertexArray(0);

        return new Mesh(vaoId, vboIds, iboId, indices.length, texture);
    }

    private static List<Integer> createVbos(VboData[] vboDataArray) {
        List<Integer> vboIds = new ArrayList<>();

        for(int i = 0; i < vboDataArray.length; i++) {
            float[] data = vboDataArray[i].getData();
            int interval = vboDataArray[i].getInterval();

            int vboId = glGenBuffers();

            FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(data.length);
            vertexBuffer.put(data).flip();

            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
            glEnableVertexAttribArray(i);
            glVertexAttribPointer(i, interval, GL_FLOAT, false, 0, 0);
            glDisableVertexAttribArray(i);
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            vboIds.add(vboId);
        }

        return vboIds;
    }

    private static int createIbo(int[] indices) {
        int iboId = glGenBuffers();

        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);
        indexBuffer.put(indices).flip();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        return iboId;
    }
}
