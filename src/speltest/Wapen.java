/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.util.ArrayList;

/**
 *
 * @author wouter
 */
public class Wapen {
    
    private ArrayList<Kogel> missiles;
    private int ammo;
    private int aantalKogels = 1;
    
    public Wapen(int aantalKogels){
          setAantalKogels(aantalKogels);
          missiles = new ArrayList<Kogel>();
    }
    public boolean shoot(int height, int y, int x, int width){
        boolean schiet = false;
        if (ammo > 0 && ammo >= aantalKogels) {
            schiet = true;
            if(aantalKogels == 1){
                missiles.add(new Kogel(x + width, y - height / 50));
            } if(aantalKogels == 2 ){
                missiles.add(new Kogel(x + width, y + height / 50));
                missiles.add(new Kogel(x + width, y - (height / 50) - 80));
            }
            ammo = ammo - aantalKogels;
        }
        return schiet;
    }
    
    public void addKogels(int i) {
        setAantalKogels(getAantalKogels() + i);
    }
    
    public ArrayList<Kogel> getMissiles() {
        return missiles;
    }

    public void reset(){
         ammo = 20;
         aantalKogels = 1;
    }
    public int getAantalKogels() {
        return ammo;
    }

    public void setAantalKogels(int aantalKogels) {
        this.ammo = +aantalKogels;
    }

    public void upgrade() {
        if(aantalKogels < 2){
             aantalKogels++;
        }
    }
}
