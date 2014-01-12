/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import databank.EnemyInitializer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author wouter
 */
public class Board extends JPanel implements ActionListener {

    protected SoundManager soundManager;
    private Timer timer;
    protected Speler speler;
    private ArrayList<Enemy> aliens = new ArrayList();
    private ArrayList<PowerUp> powerUp = new ArrayList();
    private int level = 1, counter = 0, powerUpTekstY = 250, snelheidEnemies;
    private Map map, map2;
    private Hud hud;
    private boolean power, pause, running, startScreen, gewonnen,levelScreen;
    private String powerTekst = "";
    private CollisionDetection collision;
    private int breedte, hoogte;
    private Scherm start, eindScherm;
    private EnemyInitializer enemyFac = new EnemyInitializer();

    public Board(String naam, Dimension dim) {
        soundManager = new SoundManager();
        breedte = (int) dim.getWidth();
        hoogte = (int) dim.getHeight();
        start = new StartScherm(naam, this);
        eindScherm = new EindScherm(this);
        add(eindScherm);
        add(start);
        speler = new Speler(60, 400, 20, naam);
        setFocusable(true);
        setBackground(Color.BLACK);
        startScreen = true;
        setSize(breedte, hoogte);
        map = new Map(4);
        hud = new Hud(100, 100);
        map2 = new Map(10);
        collision = new CollisionDetection();
        timer = new Timer(1, this);
        timer.start();
        addListener(speler);
    }

    public void addListener(Speler s) {

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                movePlayer(ke);
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                speler.setRichting("left");
            }

            private void movePlayer(KeyEvent ke) {
                if (ke.getKeyCode() == 27) {
                    changeGameState();
                } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!isRunning()) {
                        reset();
                        start();
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (speler.fire()) {
                        soundManager.playSound("pew");
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    speler.setRichting("left");
                } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    speler.setRichting("up");
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    speler.setRichting("down");
                }
            }
        });
    }

    public void initAliens(int level, int snelheid) {
        aliens = enemyFac.createEnemies(level, breedte, hoogte, snelheid + 4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //afhandelen van de power up tekst en het bijhouden van de counter
        if (!isPause()) {
            counter++;
            powerUpTekstY--;
            if (getCounter() % 500 == 0) {
                getSpeler().addKogels(5);
            }
            if (getCounter() == 5000) {
                setCounter(0);
            }
            if (isPower()) {
                powerTekst = powerUp.get(0).getPowerUpNaam();
                powerUp.remove(0);
                setPower(false);
            }
            if (getAliens().isEmpty()) {
                setRunning(false);
            }
            for (int f = 0; f < powerUp.size(); f++) {
                //hier alle verschillende power ups afgaan en de juiste actie doen
                if (powerUp.get(f) instanceof Ammo) {
                    getSpeler().addKogels(5 * getLevel() / 2);
                } else if (powerUp.get(f) instanceof WeaponUpgrade) {
                    getSpeler().upgradeWapen();
                    getSpeler().addKogels(getSpeler().getAantalKogels() * 2);
                }
                setPowerUpTekstY(200);
                setPower(true);
            }
            //de positie van de kogels en enemies updaten
            ArrayList<Kogel> ms = speler.getMissiles();
            for (int i = 0; i < ms.size(); i++) {
                Kogel m = ms.get(i);
                if (m.isVisible()) {
                    m.move(hoogte, breedte);
                } else {
                    ms.remove(i);
                }
            }
            for (int i = 0; i < aliens.size(); i++) {
                Enemy a = aliens.get(i);
                if (a.isVisible()) {
                    a.move(hoogte, breedte);
                } else {
                    aliens.remove(i);
                }
            }
            speler.move(speler.getSnelheid(),breedte,hoogte);
            map.scrollLeft(breedte);
            map2.scrollLeft(breedte);
            checkCollisions(speler, aliens, powerUp, running);
            repaint();
        }
    }

    public void checkCollisions(Speler player, ArrayList<Enemy> enemies, ArrayList<PowerUp> powerUps, boolean go) {
        running = collision.checkCollision(player, enemies, powerUps, go);
    }

    public void reset() {
        removeAll();
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).setVisible(false);
        }
        if (gewonnen) {
            setLevel(getLevel() + 1);
        }
        levelScreen = true;
        startScreen = false;
        gewonnen = false;
        speler.reset();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));

        if (isStartScreen()) {
            start.setVisible(true);
        } 


        if (isPause()) {
            g2d.setColor(Color.white);
            g2d.drawString("pause, press escape to resume", 300, 300);
        }

        if (isRunning()) {
            g2d.drawImage(getMap().getImage(), getMap().getX(), getMap().getY(), this);
            g2d.drawImage(getMap2().getImage(), getMap2().getX(), getMap2().getY(), this);
            getSpeler().draw(g2d, this);

            ArrayList<Kogel> ms = getSpeler().getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                Kogel m = ms.get(i);
                m.draw(g2d, this);
            }

            for (int i = 0; i < getAliens().size(); i++) {
                Enemy a = getAliens().get(i);
                if (a.isVisible()) {
                    a.tekenHealth(g2d, 4);
                    a.draw(g2d, this);
                }
            }

            hud.drawHud(g2d, hoogte, breedte, powerUpTekstY, speler.getAantalKogels(), level, aliens.size(), speler.getScore(), powerTekst);
        } else if (running == false && !isStartScreen()) {
            if (aliens.isEmpty()) {
                gewonnen = true;
            }
            add(start);
        }
    }

    private void changeGameState() {
        if (pause == true) {
            pause = false;
        } else {
            pause = true;
        }
    }

    public void start() {
        setLevel(level);
        snelheidEnemies = 1;
        initAliens(level, snelheidEnemies);
        removeAll();
        running = true;
        startScreen = false;
    }

    public ArrayList<Enemy> getAliens() {
        return aliens;
    }

    public void setAliens(ArrayList<Enemy> aliens) {
        this.aliens = aliens;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Map getMap() {
        return map;
    }

    public Map getMap2() {
        return map2;
    }

    public void setMap2(Map map2) {
        this.map2 = map2;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getBreedte() {
        return breedte;
    }

    public void setBreedte(int breedte) {
        this.breedte = breedte;
    }

    public int getHoogte() {
        return hoogte;
    }

    public void setHoogte(int hoogte) {
        this.hoogte = hoogte;
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public ArrayList<PowerUp> getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(ArrayList<PowerUp> powerUp) {
        this.powerUp = powerUp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isStartScreen() {
        return startScreen;
    }

    public int getPowerUpTekstY() {
        return powerUpTekstY;
    }

    public void setPowerUpTekstY(int powerUpTekstY) {
        this.powerUpTekstY = powerUpTekstY;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public String getPowerTekst() {
        return powerTekst;
    }
}