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
public class NormalEnemy extends Enemy {

    
    
    public NormalEnemy(int x, int y, int snelheid) {
        super(x, y, snelheid);
        setHealth(2);
        startHealth = health;
    }

    @Override
    public void setImage() {
        image = new ImageIcon(this.getClass().getClassLoader().getResource("images/bal2.png")).getImage();
    }

    @Override
    public void move(int maxX, int maxY) {
        beweging.move(maxX, maxY, this);
    }

    @Override
    public int getScore() {
        return startHealth * 10;
    }

    @Override
    public void setBeweging(MoveBeweging move) {
        beweging = new SchuinBeweging();
    }
}
