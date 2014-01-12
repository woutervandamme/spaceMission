/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.util.ArrayList;
import java.util.Random;
import speltest.BossEnemy;
import speltest.Enemy;
import speltest.HardEnemy;
import speltest.NormalEnemy;

/**
 *
 * @author wouter
 */
public class EnemyInitializer {

    private Random r = new Random();

    public ArrayList<Enemy> createEnemies(int level, int breedte, int hoogte, int snelheid) {

        ArrayList<Enemy> enemies = new ArrayList<>();

        if (level == 1) {
            for (int i = 0; i < 8; i++) {
                enemies.add(new NormalEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            }

        } else if (level == 2) {
            for (int i = 0; i < 7; i++) {
                enemies.add(new NormalEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            }
            enemies.add(new HardEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            enemies.add(new HardEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
        } else if (level == 3) {
            enemies.add(new BossEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            for (int i = 0; i < 5; i++) {
                enemies.add(new NormalEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            }

        } else if (level == 4) {
            for (int i = 0; i < 200; i++) {
                enemies.add(new NormalEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            }
            enemies.add(new HardEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
            enemies.add(new HardEnemy(r.nextInt(300) + breedte, r.nextInt(hoogte - 100), snelheid + 2));
        }
        return enemies;
    }
}
