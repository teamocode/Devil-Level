package com.shredquest.zombiegame.application.collision;

import com.shredquest.zombiegame.application.entities.Entity;
import org.joml.Vector3f;

public abstract class Collision {
    public static boolean doesVectorIntrsctEntity(Vector3f vector, float angle, Entity entity, float step, float distance) {
        float dx = distance * (float) Math.cos(angle);
        float dz = distance * (float) Math.sin(angle);
        //Vector3f target = new Vector3f(vector.x + dx, 0f, vector.z + dz);
        Vector3f movement = new Vector3f(dx, 0f, dz);
        movement.normalize();
        movement.mul(step);
        System.out.println(movement);

        Vector3f entPosition = entity.getPosition();
        Vector3f entSize = new Vector3f(1f, 1f, 1f);

        if((movement.x > entPosition.x - entSize.x / 2f)
                && (movement.x < entPosition.x + entSize.x / 2f)
                && (movement.z > entPosition.z - entSize.z / 2f)
                && (movement.z < entPosition.z + entSize.z / 2f)) {
            System.out.println("Collision!");
        }

        return false;
    }

    public static boolean doesEntityIntrsctEntity(Entity entityA, Entity entityB) {
        Vector3f positionA = entityA.getPosition();
        Vector3f sizeA = new Vector3f(1f, 1f, 1f);
        Vector3f positionB = entityB.getPosition();
        Vector3f sizeB = new Vector3f(1f, 1f, 1f);

        if((positionA.x + sizeA.x / 2f > positionB.x - sizeB.x / 2f) &&
        (positionA.x - sizeA.x / 2f < positionB.x + sizeB.x / 2f) &&
                (positionA.z + sizeA.z / 2f > positionB.z - sizeB.z) &&
                (positionA.z - sizeA.z / 2f < positionB.z + sizeB.z)) {
            return true;
        }

        return false;
    }
}
