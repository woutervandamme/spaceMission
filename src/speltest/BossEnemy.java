/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import javax.swing.ImageIcon;

/**
 *
 * @author wouter
 */
public class BossEnemy extends Enemy{
   
    private Wapen wapen;
    
     public BossEnemy(int x, int y, int snelheid) {
        super(x, 300, snelheid);
        wapen = new Wapen(2);
        setHealth(25);
        startHealth = health;
    }

    @Override
    public void setImage() {
        image = new ImageIcon(this.getClass().getClassLoader().getResource("images/baas.png")).getImage();
    }

    @Override
    public int getScore() {
        return 1000;
    }

    @Override
    public void move(int maxX, int maxY) {
        beweging.move(maxX, maxY, this);
    }    

    @Override
    public void setBeweging(MoveBeweging move) {
        beweging = new Bossbeweging();
    }

  
}
