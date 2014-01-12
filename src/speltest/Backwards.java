/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author wouter
 */
public class Backwards extends Board{
    
    public Backwards(String naam, Dimension dim) {
        super(naam, dim);
    }
    
    
      @Override
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
            }

            private void movePlayer(KeyEvent ke) {
               if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!isRunning()) {
                        reset();
                        start();
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (speler.fire()) {
                        soundManager.playSound("pew");
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    speler.setRichting("left");
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    speler.setRichting("up");
                } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    speler.setRichting("down");
                }
            }
        });
    }
}
