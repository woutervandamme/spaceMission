/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author wouter
 */
public abstract class Scherm extends JPanel {
    
    
    public Scherm(){
        super();
        setBackground(Color.black);
        setForeground(Color.white);
        setLayout(null);
        setPreferredSize(new Dimension(600, 500));
    }
}
