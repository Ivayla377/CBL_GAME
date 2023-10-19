import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Stopwatch {
    
    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
        
    });
}
