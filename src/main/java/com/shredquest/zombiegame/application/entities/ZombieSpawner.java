package com.shredquest.zombiegame.application.entities;

import com.shredquest.zombiegame.application.loader.Loader;
import com.shredquest.zombiegame.application.renderer.Mesh;
import com.shredquest.zombiegame.application.renderer.MeshCreator;
import com.shredquest.zombiegame.application.renderer.Texture;
import org.joml.Vector3f;

import java.util.List;
import java.util.Random;

public class ZombieSpawner extends Entity{
    public ZombieSpawner(String identifier) {
        super("zombie_spawner_" + identifier);
    }

    public void spawn(int amount, List<Zombie> zombies, Mesh zombieMesh) {
        for(int i = 0; i < amount; i++) {
            Zombie zombie = new Zombie();
            zombie.setMesh(zombieMesh);
            Random random = new Random();
            float x = this.getPosition().x + (-2 + random.nextFloat() * (2 + 2));
            float z = this.getPosition().z + (-2 + random.nextFloat() * (2 + 2));
            zombie.setPosition(new Vector3f(x, this.getPosition().y, z));
            zombies.add(zombie);
        }
    }
}
