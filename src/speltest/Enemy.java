/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

/**
 *
 * @author wouter
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public abstract class Enemy extends Draw implements Movable {

    protected int width, height, health, startHealth, snelheid, richting,score;
    protected Random r = new Random();
    private PowerUp power;
    private PowerUpFactory factory = new PowerUpFactory();
    protected MoveBeweging beweging;
    
    public Enemy(int x, int y, int snelheid) {
        super(x, y);
        setVisible(true);
        startHealth = health;
        setImage();
        width = getImage().getWidth(null);
        height = getImage().getHeight(null);
        this.snelheid = snelheid;
        richting = r.nextInt(3);
        setBeweging(beweging);
    }
    
    public abstract void setBeweging(MoveBeweging move);

    public abstract void setImage();
    
    public void setHealth(int health) {
        this.health = health;
    }  
    
    public abstract int getScore();
   
    public boolean hit(int hitDamage) {
        boolean dood = false;
        health--;
        if (checkDood(health)) {
            dood = true;
            int i = r.nextInt(5);
            if (i < 5) {
                power = factory.createPowerUp();
            }
        }
        return dood;
    }

    private boolean checkDood(int health) {
        boolean dood = false;
        if (health < 0) {
            dood = true;
        }
        return dood;
    }

    public PowerUp getPowerUp() {
        return power;
    }

    public abstract void move(int maxX, int maxY);

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    public void tekenHealth(Graphics2D g, int level) {
        g.setColor(Color.green);
        g.fillRect(getX(), getY(), health* (30 / level), 1);
    }

    public void changeRichting() {
        if (richting < 3) {
            richting++;
        } else {
            richting = 1;
        }
    }
}
