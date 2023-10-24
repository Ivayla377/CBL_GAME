import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Player {

    public int foodX;
    public int foodY;
    public int speed;
    public int boostSpeed;
    public int numberOfBoosts = 3;
    
    Image player;
    String playertoString;

    public Player() {
        foodX = 234;
        foodY = 716;
        speed = 3;
        boostSpeed = 7;
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
        if (keyInput.isLeft && keyInput.boast && numberOfBoosts > 0) {
            foodX -= speed*boostSpeed;
            if(boostSpeed > 0) {
                boostSpeed--;
                if(boostSpeed == 0) { System.out.println("boast ended"); numberOfBoosts--;}
            }
        }
        if (keyInput.isRight && keyInput.boast && numberOfBoosts > 0) {
            foodX += speed*boostSpeed;
            if(boostSpeed > 0) {
                boostSpeed--;
                if(boostSpeed == 0) { numberOfBoosts--;}
            }
        }
    }
}
