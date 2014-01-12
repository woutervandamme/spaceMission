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
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Speler extends Draw implements Movable {

    private int width, height, snelheid, score;
    private String naam = "unknown";
    private Wapen wapen;
    private ImageIcon i = new ImageIcon(this.getClass().getClassLoader().getResource("images/player.png"));
    private String richting = "left";

    public Speler(int x, int y, int snelheid, String naam) {
        super(x, y);
        setNaam(naam);
        setSnelheid(snelheid);
        setImage(i.getImage());
        wapen = new Wapen(30);
        setVisible(true);
        width = 100;
        height = 50;
    }

    public String getNaam() {
        return naam;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    public int getSnelheid() {
        return snelheid;
    }

    private void setSnelheid(int snelheid) {
        this.snelheid = snelheid;
    }

    public int getAantalKogels() {
        return wapen.getAantalKogels();
    }

    public void setAantalKogels(int aantalKogels) {
        wapen.setAantalKogels(aantalKogels);
    }

    public void upgradeWapen() {
        wapen.upgrade();
    }

    public ArrayList<Kogel> getMissiles() {
        return wapen.getMissiles();
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void removeScore(int score) {
        this.score = -score;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    public void addKogels(int i) {
        wapen.addKogels(i);
    }

    public boolean fire() {
        return wapen.shoot(height, getY(), getX(), width);
    }

    void reset() {
        wapen.reset();
        setVisible(true);
        setX(40);
        setY(300);
    }

    public void up() {
        setY(getY() - snelheid);
    }

    public String getRichting() {
        return richting;
    }

    public void setRichting(String richting) {
        this.richting = richting;
    }

    public void move(int snelheid,int maxX, int maxY) {
        switch (richting) {
            case "up":
                if(getY() > 0)
                    setY(getY() - snelheid);
                break;
            case "down":
                if(getY() < maxY)
                setY(getY() + snelheid);
                break;
            case "left":
                if(getX() < 0)
                setX(getX() - snelheid);
                break;
        }
    }
}
