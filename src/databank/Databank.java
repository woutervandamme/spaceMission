/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.util.ArrayList;
import speltest.Speler;

/**
 *
 * @author wouter
 */
public interface Databank {
    
    public ArrayList<String> leesScores();
    public void schrijfScoreWeg(Speler p);
}
