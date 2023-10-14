import java.awt.*;
import javax.swing.*;

/**
 * Class for all enemies.
 */
public class Enemies extends Enemy {
    
    
    Enemy [] enemies = new Enemy[5];

    /**
     * Initialize array of enemies.
     */
    public Enemies() {
        for (int i = 0; i < 5; i++) {
            enemies[i] = new Enemy();
        }
    }

    public void updateFallEnemies() {

        for (int i = 0; i < 5; i++) {
            if ((enemies[i].enemyY + enemies[i].speed) < 780) {
                enemies[i].enemyY += enemies[i].speed;
            }
            if ((enemies[i].enemyY + 50) >= 730) {
                enemies[i].enemyY = 40;
                enemies[i].resetX();

            } 
            if ((enemies[i].enemyY + 50  >= foodY) && 
            (enemies[i].enemyX >= foodX - 64) && 
            (enemies[i].enemyX <= foodX + 64)) {
            
                Panel.lives -= 1;
                enemies[i].resetY();
            }
        }
    }

    public void drawFallEnemy(Graphics graphics) {
        for (int i = 0; i < 5; i++) {
            graphics.drawImage(enemies[i].enemy, enemies[i].enemyX, enemies[i].enemyY, null);
        }
    }
}
