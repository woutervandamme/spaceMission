/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author wouter
 */
public abstract class Draw {

    protected Image image;
    private boolean visible;
    private int x, y;

    public Draw(int x, int y) {
        setX(x);
        setY(y);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g, Board b) {
        if (isVisible()) {
            g.drawImage(getImage(), getX(), getY(), b);
        }
    }
}
