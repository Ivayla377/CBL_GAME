/**
 * stage 1 enemy class.
 */
public class Stage3Enemy extends Enemy {

    private int speed = 4;
    private String imageName = "Images/tedi.png";

    /**
     * Constructor.
     * @param x position x
     * @param y position y
     */
    public Stage3Enemy(int x, int y) {
        super(x, y);
        initEnemyImage(imageName);
    }

    @Override
    public int getEnemySpeed() {
        return speed;
    }
}
