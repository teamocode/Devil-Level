package com.shredquest.zombiegame.application.logic;

import com.shredquest.zombiegame.application.collision.Collision;
import com.shredquest.zombiegame.application.entities.*;
import com.shredquest.zombiegame.application.input.Input;
import org.joml.Vector3f;

import java.util.List;

public class DevilLevelLogic implements Logic {
    private static final float BULLET_DISTANCE = 30f;

    private int zombieCount;
    private int zmbSpwnrCapacity;

    public DevilLevelLogic() {
        zombieCount = 0;
        zmbSpwnrCapacity = 1;
    }

    @Override
    public void govern(EntityManager entityManager, Input input) {
        Player player = entityManager.getPlayer();
        List<ZombieSpawner> zombieSpawners = entityManager.getZmbSpwnrs();
        List<Zombie> zombies = entityManager.getZombies();
        List<Bullet> bullets = entityManager.getBullets();
        Camera camera = entityManager.getCamera();

        if(player.getHealth() > 0f) {
            player.handle(input);
            camera.anchorTo(player);

            if(player.isShooting()) {
                player.shoot(bullets);
            }

            for(int i = 0; i < bullets.size(); i++) {
                bullets.get(i).move();

                Vector3f initPosition = bullets.get(i).getInitPosition();
                Vector3f currentPosition = bullets.get(i).getPosition();
                float dx = currentPosition.x - initPosition.x;
                float dz = currentPosition.z - initPosition.z;
                float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
                if(distance >= BULLET_DISTANCE) {
                    bullets.remove(i);
                }
            }

            if(zombieCount == 0) {
                for(ZombieSpawner zombieSpawner : zombieSpawners) {
                    zombieSpawner.spawn(zmbSpwnrCapacity, zombies, entityManager.getZombieMesh());

                }
                zombieCount = zombies.size();
                zmbSpwnrCapacity++;
            }

            for(int i = 0; i < zombies.size(); i++) {
                zombies.get(i).face(player.getPosition());

                zombies.get(i).moveToward(player.getPosition());

                for(Bullet bullet : bullets) {
                    if(Collision.doesEntityIntrsctEntity(zombies.get(i), bullet)) {
                        zombies.remove(i);
                        zombieCount--;
                    }
                }
            }
        }
    }
}
