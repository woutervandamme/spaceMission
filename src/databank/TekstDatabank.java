/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import speltest.Speler;

/**
 *
 * @author wouter
 */
public class TekstDatabank implements Databank {

    @Override
    public ArrayList<String> leesScores() {
        ArrayList<String> lijst = new ArrayList<String>();
        InputStream in = this.getClass().getResourceAsStream("scores.txt");
        InputStreamReader inreader = new InputStreamReader(in);
        BufferedReader breader = new BufferedReader(inreader);
        String lijn = "";
        try {
            lijn = breader.readLine();
            while (lijn != null) {
                lijst.add(lijn);
                lijn = breader.readLine();
            }
        } catch (IOException e) {
        }
        
        Collections.sort(lijst);
        Collections.reverse(lijst);
        return lijst;
    }
   
    @Override
    public void schrijfScoreWeg(Speler s) {
        File myFile = new File("src/databank/scores.txt");
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(myFile,true));
            output.write( s.getScore() +": "+s.getNaam()+ "\n");
            output.close();
        } catch (IOException ex) {
        }

    }

}
