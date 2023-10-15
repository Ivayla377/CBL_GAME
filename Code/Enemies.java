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

    public void drawFallEnemy(Graphics graphics) {
        for (int i = 0; i < 5; i++) {
            graphics.drawImage(enemies[i].enemy, enemies[i].enemyX, enemies[i].enemyY, null);
        }
    }
}
