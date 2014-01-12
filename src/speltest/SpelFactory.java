/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Dimension;

/**
 *
 * @author wouter
 */
public class SpelFactory {
    
    
    public Board createSpel(String type,String naam, Dimension dim){
      Board b;
        switch (type) {
            case "spacecrafter":
                b = new Board(naam, dim);
                break;
            case "teleporter":
                b = new Teleporter(naam, dim);
                break;
            default:
                b = new Backwards(naam,dim);
                break;
        }
        
        return b;
    }
}
