/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import databank.Databank;
import databank.TekstDatabank;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author wouter
 */
public class EindScherm extends Scherm implements ActionListener{
    
    
   
    private JButton exit = new JButton("X");
    private JButton retry = new JButton("retry");
    private Board b;
    private Databank db = new TekstDatabank();
    
    public EindScherm(Board b){
        this.b = b;
        setVisible(false);
        init();
        add(exit);
        add(retry);
    }
    
    public void init(){
        
      //  exit.setBounds(740, 20, 50, 50);
        exit.setBackground(Color.black);
        exit.setBorder(new knopBorder());
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        
      //  retry.setBounds(250, 260, 150, 50);
        retry.setBackground(Color.black);
        retry.setBorder(new knopBorder());
        retry.setForeground(Color.white);
        retry.addActionListener(this);
        
    }

    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        } else if(e.getSource() == retry){
            b.reset();
            b.start();
        }
    }
    
}
