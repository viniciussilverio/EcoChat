
package chataps5;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Emoticon extends JFrame {
    public static JFrame Emoticon(){
        JFrame frame = new JFrame(" Emoticon ");
        frame.setUndecorated(true);
        ImageIcon icon = new ImageIcon(Emoticon.class.getResource("/media/crying.png"));
        JLabel label = new JLabel(icon);
        label.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e)  
        {
            frame.dispose();
        }  
        }); 
        frame.add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
}
