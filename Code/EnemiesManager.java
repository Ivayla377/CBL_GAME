import java.awt.*;
import java.util.*;

/**
 * Class that contains all functions related to enemy motion, graphics, and update.
 */
public class EnemiesManager {
    public ArrayList<Enemy> enemies;
    private Random rand = new Random();
    int numLanes = 6;
    int numRows = 3;

    public EnemiesManager() {
        enemies = new ArrayList<>();
    }

    /**
     * Initiate enemies according to stage.
     * @param numEnemies The number of enemies to be spawned
     */
    public void initStage1Enemies(int numEnemies) {
        clearEnemies();
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

    /**
     * Replace old enemy and get new enemies according to stage.
     * @param numEnemies The number of enemies to be spawned
     */
    public void initStage2Enemies(int numEnemies) {
        clearEnemies();
        int lane;
        int row;
        for (int i = 0; i < numEnemies; i++) {

            do {
                lane = rand.nextInt(numLanes);
                row = rand.nextInt(numRows);
            } while (checkForOverlap(lane, row));

            int randomX = lane * 100;
            int randomY = row * -64;
            Stage2Enemy enemy = new Stage2Enemy(randomX, randomY);
            enemies.add(enemy);
        }
    }

    /**
     * Replace old enemy and get new enemies according to stage.
     * @param numEnemies The number of enemies to be spawned
     */
    public void initStage3Enemies(int numEnemies) {
        clearEnemies();
        int lane;
        int row;
        for (int i = 0; i < numEnemies; i++) {

            do {
                lane = rand.nextInt(numLanes);
                row = rand.nextInt(numRows);
            } while (checkForOverlap(lane, row));

            int randomX = lane * 100;
            int randomY = row * -64;
            Stage3Enemy enemy = new Stage3Enemy(randomX, randomY);
            enemies.add(enemy);
        }
    }

    /**
     * Replace old enemy and get new enemies according to stage.
     * @param numEnemies The number of enemies to be spawned
     */
    public void initStage4Enemies(int numEnemies) {
        clearEnemies();
        int lane;
        int row;
        for (int i = 0; i < numEnemies; i++) {

            do {
                lane = rand.nextInt(numLanes);
                row = rand.nextInt(numRows);
            } while (checkForOverlap(lane, row));

            int randomX = lane * 100;
            int randomY = row * -64;
            Stage4Enemy enemy = new Stage4Enemy(randomX, randomY);
            enemies.add(enemy);
        }
    }

    
    /**
     * Checks for overlapping enemies in one wave.
     * @param lane The lane in which an enemy is positioned
     * @param row The row in which it is positioned 
     * @return true -> characters completely overlap; false -> no overlap
     */
    private boolean checkForOverlap(int lane, int row) {
        int newEnemyX = lane * 100;
        int newEnemyY = row * -64;

        for (Enemy enemy : enemies) {
            if (checkForOverlap(newEnemyX, newEnemyY, enemy)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForOverlap(int newEnemyX, int newEnemyY, Enemy enemy) {
        if (newEnemyX + 64 >= enemy.enemyX + 64 
                && newEnemyX <= enemy.enemyX 
                && newEnemyY <= enemy.enemyY + 64 
                && newEnemyY + 64 >= enemy.enemyY) {
            return true; 
        }
        return false;
    }

    /**
     * Resets the position of each enemy as it goes under the screen.
     * @param enemy Enemy object being reset
     */
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

    /**
     * Method that draws the enemies on screen.
     * @param graphics Graphics used to draw
     */
    public void drawEnemies(Graphics graphics) {
        for (Enemy enemy : enemies) {
            graphics.drawImage(enemy.enemyImage, enemy.enemyX, enemy.enemyY, null);
        }
    }

    public void clearEnemies() {
        enemies.clear();
    }
}
