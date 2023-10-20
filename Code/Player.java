import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Player {
    
    Image player;
    String playertoString;

    public Player() {
        playertoString = "Croissant";
        initPlayer();
    }

    public void initPlayer() {
        loadPlayer();
    }

    public void loadPlayer() {
        ImageIcon ii = new ImageIcon("Images/" + playertoString + ".png");
        player = ii.getImage();
    }
}
