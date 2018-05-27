package chataps5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TocaSom {
    public static void Tocar(){
        Tocar(1);
    }
    
    public static void Tocar(int som){
        try {
            Clip clip = AudioSystem.getClip();

            URL file1, file2, file3;
            file1 =  TocaSom.class.getResource("/media/alert.wav");
            file2 =  TocaSom.class.getResource("/media/vitas.wav");
            file3 =  TocaSom.class.getResource("/media/guitarra.wav");
            switch(som){
                case 1:
                    clip.open(AudioSystem.getAudioInputStream(file1));
                    break;
                case 2:
                    clip.open(AudioSystem.getAudioInputStream(file2));
                    break;
                case 3:
                    clip.open(AudioSystem.getAudioInputStream(file3));
                    break;
                default:
                    clip.open(AudioSystem.getAudioInputStream(file1));
                break;
            }
            clip.start();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TocaSom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            Logger.getLogger(TocaSom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
