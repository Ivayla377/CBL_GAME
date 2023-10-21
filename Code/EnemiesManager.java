import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class EnemiesManager{
    public ArrayList<Enemy> enemies;
    private Random rand = new Random();
    int numLanes = 6;
    int numRows = 3;

    public EnemiesManager() {
        enemies = new ArrayList<>();
    }

    public void initStage1Enemies(int numEnemies) {
        int lane;
        int row;
        for (int i = 0; i < numEnemies; i++) {

            do {
                lane = rand.nextInt(numLanes);
                row = rand.nextInt(numRows);
            } while (checkForOverlap(lane, row));

            int randomX = lane * 100;
            int randomY = row * -64;
            
            Stage1Enemy enemy = new Stage1Enemy(randomX, randomY);
            enemies.add(enemy);
        }
    }

    public void initStage2Enemies(int numEnemies) {
        clearEnemies();
        for (int i = 0; i < numEnemies; i++) {
            int lane = rand.nextInt(6);
            int randomX = lane * 100;
            int randomY = rand.nextInt(-40, 100);
            Stage2Enemy enemy = new Stage2Enemy(randomX, randomY);
            enemies.add(enemy);
        }
    }
    
    private boolean checkForOverlap(int lane, int row) {
        int newEnemyX = lane * 100;
        int newEnemyY = row * -64;

        for (Enemy enemy : enemies) {
            if (newEnemyX + 64 >= enemy.enemyX + 64 &&
                newEnemyX <= enemy.enemyX &&
                newEnemyY <= enemy.enemyY + 64 &&
                newEnemyY + 64 >= enemy.enemyY) {
                return true; 
            }
        }
        return false;
    }

    public void resetEnemyPosition(Enemy enemy) {
        int lane;
        int row;
        do {
            lane = rand.nextInt(numLanes);
            row = rand.nextInt(numRows);
        } while (checkForOverlap(lane, row));
        enemy.setX(lane);
        enemy.setY(row);
    }

    public void drawEnemies(Graphics graphics) {
        for (Enemy enemy : enemies) {
            graphics.drawImage(enemy.enemyImage, enemy.enemyX, enemy.enemyY, null);
        }
    }

    public void clearEnemies() {
        enemies.clear();
    }
}
