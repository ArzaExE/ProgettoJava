package PratoFioritoGame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * 
 * Classe che gestisce la riproduzione della musica durante il gioco
 * 
 * 
 * @version 19.02.24
 * @author christian.arzani
 */

public class Sound {
    public static synchronized void play(final String fileName)
    {
        // Note: use .wav files
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();

    }
}