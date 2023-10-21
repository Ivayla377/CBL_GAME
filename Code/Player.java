import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Player {

    public int foodX;
    public int foodY;
    public int speed;
    
    Image player;
    String playertoString;

    public Player() {
        foodX = 234;
        foodY = 716;
        speed = 3;
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
    
    public void updatePlayer(KeyInput keyInput) {
        if (keyInput.isLeft && (foodX - speed) > 0) {
            foodX -= speed;
        }
        if (keyInput.isRight && (foodX + speed) < 536) {
            foodX += speed;
        }
    }
}
