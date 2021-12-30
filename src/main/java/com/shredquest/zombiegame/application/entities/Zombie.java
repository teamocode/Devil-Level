package com.shredquest.zombiegame.application.entities;

import org.joml.Vector3f;

public class Zombie extends Entity {
    public static final float ZOMBIE_STEP = 0.1f;

    public Zombie() {
        super("zombie");
    }

    public void attack(Player player, float damageAmount) {
        player.affectHealth(damageAmount);
    }

    public void moveToward(Vector3f target) {
        Vector3f position = this.getPosition();
        Vector3f movement = new Vector3f(target.x - position.x, 0f, target.z - position.z);
        movement.normalize();
        movement.mul(ZOMBIE_STEP);
        offsetPosition(movement);
    }
}
