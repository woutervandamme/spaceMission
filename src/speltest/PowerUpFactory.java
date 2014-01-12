/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.util.Random;

/**
 *
 * @author wouter
 */
public class PowerUpFactory {
    
    public PowerUp power = null;
    private Random r = new Random();
    
    public PowerUp createPowerUp(){
        //random power up aanmaken
        int i = r.nextInt(10);     
        if(i < 5){
            power = new Ammo();
        }//else if (i == 8){
           // power = new SlowMo();
         else if (i == 9){
            power = new WeaponUpgrade();
        }
        
        return power;
    }
}
