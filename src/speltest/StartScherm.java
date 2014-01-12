/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import databank.Databank;
import databank.XMLDatabank;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author wouter
 */
public class StartScherm extends Scherm implements ActionListener {

    private JLabel controlLabel = new JLabel();
    private JLabel naam = new JLabel();
    private JButton exit = new JButton("exit");
    private JButton hardKnop = new JButton("play");
    private JButton highScores = new JButton("highscores");
    private JButton controls = new JButton("controls");
    private Board b;
    private Databank db = new XMLDatabank();
    private JList scores = new JList(db.leesScores().toArray());

    public StartScherm(String naamSpeler, Board b) {
        this.b = b;
        setVisible(false);
        init(naamSpeler);
    }

    private void init(String naamSpeler){

        
        controlLabel.setText("<html>press the arrows to move<br>press the spacebar to fire<br><br>press escape to pause the game<br>"
                + "press enter to restart at the end of the game</html>");
        controlLabel.setForeground(Color.white);
        controlLabel.setBounds(200, 300, 500, 200);
        controlLabel.setVisible(false);
        add(controlLabel);
        
        scores.setBounds(220, 380, 200, 200);
        scores.setBackground(Color.black);
        scores.setForeground(Color.white);
        scores.setVisible(false);
        add(scores);

        exit.setBackground(Color.black);
        exit.setBorder(new knopBorder());
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        exit.setBounds(450, 300, 100, 30);
        add(exit);

        hardKnop.setBackground(Color.black);
        hardKnop.setBorder(new knopBorder());
        hardKnop.setForeground(Color.white);
        hardKnop.addActionListener(this);
        hardKnop.setBounds(0, 300, 100, 30);
        add(hardKnop);

        highScores.setBackground(Color.black);
        highScores.setBorder(new knopBorder());
        highScores.setForeground(Color.white);
        highScores.addActionListener(this);
        highScores.setBounds(150, 300, 100, 30);
        add(highScores);

        controls.setBackground(Color.black);
        controls.setBorder(new knopBorder());
        controls.setForeground(Color.white);
        controls.addActionListener(this);
        controls.setBounds(300, 300, 100, 30);
        add(controls);

        naam.setForeground(Color.white);
        naam.setText("welcome " + naamSpeler );
        naam.setBounds(250, 260, 200, 30);
        add(naam);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            db.schrijfScoreWeg(b.getSpeler());
            System.exit(0);
        } else if (e.getSource() == hardKnop) {
            b.reset();
            b.start();
        } else if (e.getSource() == controls) {
            scores.setVisible(false);
            if (controlLabel.isVisible() == true) {
                controlLabel.setVisible(false);
            } else {
                controlLabel.setVisible(true);
            }
        } else if (e.getSource() == highScores) {
            controlLabel.setVisible(false);
            if (scores.isVisible() == true) {
                scores.setVisible(false);
            } else {
                scores.setVisible(true);
            }
        }
    }
}
