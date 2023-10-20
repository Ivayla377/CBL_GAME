import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;

/**
 * Run game.
 */
public class Game {

    /**
     * Conmstructor for game.
     */
    public Game() {
        JFrame game = new JFrame("YAM YAM YAM");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);

        Panel gamePlay = new Panel();
        game.add(gamePlay);

        game.pack();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

        gamePlay.startGameThread();
    }

    public static void main(String[] args) throws IOException{
        
        new Game();
        

    }
}