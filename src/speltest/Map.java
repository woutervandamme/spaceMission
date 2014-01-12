/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author wouter
 */
public class Map {
    
     private ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("images/map.png"));
     private Image image = ii.getImage();
     private int x =0,y=0,snelheid;
     

     public Map(int snelheid){
         setSnelheid(snelheid);
     }
     
   
    public Image getImage() {
        return image;
    }

    public int getSnelheid() {
        return snelheid;
    }

    public void setSnelheid(int snelheid) {
        this.snelheid = snelheid;
    }
    

    public void setImage(Image image) {
        this.image = image;
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
     
     public void scrollLeft(int maxX){
         setX(getX()-getSnelheid());
         if(getX()< -image.getWidth(null)+maxX){
             setX(0);
         }
     }
}
