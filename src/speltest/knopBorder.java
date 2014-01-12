/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class knopBorder extends AbstractBorder {

    public boolean isBorderOpaque() {
        return true;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(3, 3, 3, 3);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        
        for (int i = 0; i < 3; i++) {
            g.drawLine(x, y + height - i - 1, x + width, y + height - i - 1);
        }
        
    }
}
