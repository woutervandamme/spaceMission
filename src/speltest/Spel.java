/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author wouter
 */
public class Spel extends JFrame {

    // vijanden die schieten
    // help Jeditor pane
    // comm. tusse vensters
    // listcellRenderer highscore
    // template  oefensessie les 2 en/of 3
    // theoriestrategy muis/keyboard
    private SpelFactory spelFac = new SpelFactory();
    
    public Spel(String naam, String type) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        setSpel(type, naam, dim);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(dim);
        setUndecorated(true);
        setTitle("Space Crafter");
        setResizable(false);
        setVisible(true);
    }

    private void setSpel(String type, String naam, Dimension dim) {
       add(spelFac.createSpel(type, naam, dim));
    }

    public static void main(String[] args) {
        String naam = JOptionPane.showInputDialog(null, "geef je naam in");
        Object[] selectionValues = {"spacecrafter", "teleporter","backwards"};
        String initialSelection = "maak je keuze";
        Object selection = JOptionPane.showInputDialog(null, "welk spel wil je spelen?",
                "Game", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        Spel spel = new Spel(naam, (String) selection);
    }
}
