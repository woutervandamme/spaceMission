/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speltest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author wouter
 */
public class SoundManager {
    
    
    
    public void playSound(String sound) {
        String url = "/home/wouter/NetBeansProjects/SpelTest/src/images/" + sound +".wav";
        try {
            InputStream in = new FileInputStream(url);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            System.out.println("er is iets fout gelopen bij het afspelen van het geluid");
        }
    }
}
