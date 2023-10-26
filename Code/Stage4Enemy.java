import javax.swing.ImageIcon;
import java.awt.*;

/**
 * stage 1 enemy class.
 */
public class Stage4Enemy extends Enemy {

    private int speed = 3;
    private String imageName = "Images/momo.png";

    /**
     * Constructor.
     * @param x position x
     * @param y position y
     */
    public Stage4Enemy(int x, int y) {
        super(x, y);
        initEnemyImage(imageName);
    }

    @Override
    public int getEnemySpeed() {
        return speed;
    }
}
