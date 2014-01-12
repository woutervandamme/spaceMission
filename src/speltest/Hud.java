/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author wouter
 */
public class Hud extends Draw{
    
    private ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("images/hud.png"));

    public Hud(int x, int y){
        super(x,y);
        setImage(ii.getImage());
    }
    
    public void drawHud(Graphics2D g2d,int height, int breedte, int powerY, int kogels,int level, int aliens, int score, String power){
          g2d.drawString("aliens left:", 50,height-150);
        g2d.drawString("kogels:", 450,height -150);
        g2d.drawString("level:", 250,height-150);
        g2d.drawString("score:", 650, height-150);

        g2d.drawString("" + aliens, 50, height-120);
        g2d.drawString("" + kogels, 450,height-120);
        g2d.drawString("" +level, 250, height-120);
        g2d.drawString("" + score, 650,height-120);
        g2d.drawString(power, breedte / 2, powerY);
        
        if (kogels < 10) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.GREEN);
        }
    }
}
