import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Handle all information regarding player.
 */
public class Player {

    public int foodX;
    public int foodY;
    public int speed;
    public int boostSpeed;
    public int numberOfBoosts = 3;
    
    Image player;
    String playertoString;

    /**
     * Constructor for player.
     */
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

    /**
     * Draw player based on stage.
     */
    public void loadPlayer() {
        ImageIcon ii = new ImageIcon("Images/" + playertoString + ".png");
        player = ii.getImage();
    }

    /**
     * Update player position.
     * @param keyInput Key Input
     */
    public void updatePlayer(KeyInput keyInput) {

        pressedLeft(keyInput);

        pressedRight(keyInput);

        pressedBoostLeft(keyInput);

        pressedBoostRight(keyInput);
    }

    /**
     * Check if key to move left is pressed.
     * @param keyInput Key Listener
     */
    public void pressedLeft(KeyInput keyInput) {
        if (keyInput.isLeft && (foodX - speed) > 0) {
            foodX -= speed;
        }
    }

    /**
     * Check if key to move right is pressed.
     * @param keyInput Key Listener
     */
    public void pressedRight(KeyInput keyInput) {
        if (keyInput.isRight && (foodX + speed + 64) < 600) {
            foodX += speed;
        }
    }

    /**
     * Check if boost to the left is pressed.
     * @param keyInput Key Listener
     */
    public void pressedBoostLeft(KeyInput keyInput) {
        if (keyInput.isLeft && keyInput.boast && numberOfBoosts > 0) {
            foodX -= speed * boostSpeed;
            checkBoostLeft();
        }
    }

    /**
     * Check if boost to the right is pressed.
     * @param keyInput Key Listener
     */
    public void pressedBoostRight(KeyInput keyInput) {
        if (keyInput.isRight && keyInput.boast && numberOfBoosts > 0) {
            foodX += speed * boostSpeed;
            checkBoostLeft();
        }
    }

    /**
     * Check if there are any boosts left.
     */
    public void checkBoostLeft() {

        if (boostSpeed > 0) {
            boostSpeed--;
            if (boostSpeed == 0) { 
                System.out.println("boast ended"); 
                numberOfBoosts--;
            }
        }
    }
}
