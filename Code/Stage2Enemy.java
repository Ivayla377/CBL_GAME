import javax.swing.ImageIcon;
import java.awt.*;

/**
 * stage 1 enemy class.
 */
public class Stage2Enemy extends Enemy {

    private int speed = 4;
    private String imageName = "Images/iva2.png";

    /**
     * Constructor.
     * @param x position x
     * @param y position y
     */
    public Stage2Enemy(int x, int y) {
        super(x, y);
        initEnemyImage(imageName);
    }

    @Override
    public int getEnemySpeed() {
        return speed;
    }
}
