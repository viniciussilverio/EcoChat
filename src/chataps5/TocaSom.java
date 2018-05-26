package chataps5;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TocaSom {
    public static void Tocar(){
        Tocar(1);
    }
    
    public static void Tocar(int som){
        try {
            File toque;
            File file1 =  new File("alert.wav");
            File file2, file3;
            if(!file1.exists()) {
                file1 =  new File("..\\alert.wav");
                file2 =  new File("..\\alert.wav");
                file3 =  new File("..\\alert.wav");
            }else{
                file2 =  new File("alert.wav");
                file3 =  new File("alert.wav");
            }
            switch(som){
                case 1:
                    toque = file1;
                    break;
                case 2:
                    toque = file2;
                    break;
                case 3:
                    toque = file3;
                    break;
                default:
                    toque = file1;
                break;
            }
            AudioClip clip = Applet.newAudioClip(toque.toURL());
            clip.play();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TocaSom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
