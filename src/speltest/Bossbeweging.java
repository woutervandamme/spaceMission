/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.util.Random;

/**
 *
 * @author wouter
 */
public class Bossbeweging implements MoveBeweging{
    
    private Random r = new Random();
    @Override
    public void move(int maxX, int maxY, Enemy enemy) {
           if (enemy.getX() < -20) {
            enemy.setY(r.nextInt(maxX));
            enemy.setX(maxY);
        } else if (enemy.getY() > maxY) {
            enemy.setY(0);
        }
        enemy.setX(enemy.getX() - enemy.snelheid * 2);
    }
    
}
