/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author wouter
 */
public class CollisionDetection {
    
    
    public boolean checkCollision(Speler speler,ArrayList aliens,ArrayList powerUp,boolean running){
     
         Rectangle r3 = speler.getBounds();
        //speler tegen enemies
        for (int j = 0; j<aliens.size(); j++) {
            Enemy a = (Enemy) aliens.get(j);
            Rectangle r2 = a.getBounds();
            
            if (r3.intersects(r2)) { 
                speler.setVisible(false);
                speler.resetScore();
                a.setVisible(false);
                running = false;        
            }
        }

        ArrayList<Kogel> ms = speler.getMissiles();
        //kogels tegen enemies
        for (int i = 0; i < ms.size(); i++) {
            Kogel m = (Kogel) ms.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<aliens.size(); j++) {
                Enemy a = (Enemy) aliens.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    //kogel en enemy wegdoen als ze elkaar raken
                    //speler sterkte is default 1 
                    
                    //TODO spelers sterkt geven
                   
                    m.setVisible(false);
                    if( a.hit(1)){
                        a.setVisible(false);
                        PowerUp power = a.getPowerUp();
                        if(power != null){
                            // kijken of de enemy een powerUp had en deze toevoegen aan de lijst
                            powerUp.add(power);
                        }
                        speler.addScore(a.getScore());
                    }
                }
            }
        }
        if(aliens.isEmpty()){
            running = false;
        }
        return running;
    }
}
