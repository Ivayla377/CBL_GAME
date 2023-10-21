import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * Enemy class with standard data for each enemy object.
 */
public class Enemy {
    public int enemyX;
    public int enemyY;
    private int speed;
    public Image enemyImage;

    public Enemy(int enemyX, int enemyY) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }

    public void initEnemyImage(String fileName) {
        loadEnemyImage(fileName);
    }

    private void loadEnemyImage(String fileName) {
        ImageIcon ii = new ImageIcon(fileName);
        this.enemyImage = ii.getImage();        
    }

    public void setX(int lane) {
        enemyX = lane * 100;
    }

    public void setY(int row) {
        enemyY = row * -64;
    }

    public int getEnemySpeed() {
        return speed;
    }

    public void moveEnemy() {
        int speed = getEnemySpeed();  
        if ((enemyY + speed) < 780) {
            enemyY += speed;
        }
    }
    
    public boolean checkForCollision(int foodX, int foodY) {
        if ((enemyY + 50  >= foodY) && (enemyX >= foodX - 64) && (enemyX <= foodX + 64)) {
            return true;
        }
        return false;
    }
}
