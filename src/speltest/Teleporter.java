/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author wouter
 */
public class Teleporter extends Board {

    public Teleporter(String naam, Dimension dim) {
        super(naam, dim);
    }

    @Override
    public void addListener(Speler s) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click(me);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                click(me);
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

            private void click(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    speler.setX(me.getX());
                    speler.setY(me.getY());
                } else if (SwingUtilities.isRightMouseButton(me)) {
                    if (speler.fire()) {
                        soundManager.playSound("pew");

                    }
                    soundManager.playSound("pew");
                }
            }
        });
    }
}
