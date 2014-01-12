/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

/**
 *
 * @author wouter
 */


import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Kogel extends Draw implements Movable{

    
    private int width, height;
    private final int MISSILE_SPEED = 20;
    private  ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("images/kogel.png"));
    
    public Kogel(int x, int y) {
        super(x,y);
        setVisible(true);
        setImage(ii.getImage());
        width = getImage().getWidth(null);
        height = getImage().getHeight(null);  
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    public void move(int maxX,int maxY) {
        setX(getX() + MISSILE_SPEED);
        if (getX() > maxY)
            setVisible(false);
        }

}

