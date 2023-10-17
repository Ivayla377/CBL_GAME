import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;


/**
 * Run game.
 */
public class Game {

    public static void main(String[] args) 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFrame game = new JFrame("YAM YAM YAM");
        //JTextField textField = new JTextField(8);
        // textField.setFont(textField.getFont().deriveFont(50f));
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);

        Panel gamePlay = new Panel();
        // gamePlay.add(textField);
        game.add(gamePlay);

        game.pack();

        game.setLocationRelativeTo(null);
        game.setVisible(true);

        gamePlay.startGameThread();
        // while (gamePlay.gameThread != null) {
        //     File file = new File("Theme.wav");
        //     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        //     Clip clip = AudioSystem.getClip();
        //     clip.open(audioInputStream);
        //     clip.start();
        // }
        

    }
}