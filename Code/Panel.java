import java.awt.*;
import java.util.*;
import javax.swing.*;


/**
 *  Game Play.
 */
public class Panel extends JPanel implements Runnable {

    static final int SIZE = 32;
    static final int SCALE = 2;
    static final int COLUMN = 6;

    static final int SCREENWIDTH = 600;
    static final int SCREENHEIGHT = 780;

    int foodX = 234;
    int foodY = 716;
    int speed = 3;
    int lives = 3;
    int minutes = 0;
    int seconds = 0;
    int fps = 60;
    

    Thread gameThread;
    KeyInput keyInput = new KeyInput();
    Image digit;
    Image live;
    Color blueColor = new Color(137, 207, 240);
    Cloud cloud = new Cloud();
    Croissant croissant = new Croissant();
    Heart heart = new Heart();
    SemiColon semiColon = new SemiColon();
    GameOver gameOver = new GameOver();
    Enemies fallEnemies = new Enemies();
    Random rand = new Random(); 

    /**
     * Constructor for object of class Panel.
     */
    Panel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(blueColor);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
        this.setLayout(new FlowLayout());
        initLive();
    }

    public void initLive() {
        loadLive();
    }

    /**
     * Show how many lives left.
     */
    public void loadLive() {

        if (lives == 0) {
            ImageIcon ii = new ImageIcon("Images/0.png");
            live = ii.getImage();
        }
        if (lives == 1) {
            ImageIcon ii = new ImageIcon("Images/1.png");
            live = ii.getImage();
        }
        if (lives == 2) {
            ImageIcon ii = new ImageIcon("Images/2.png");
            live = ii.getImage();
        }
        if (lives == 3) {
            ImageIcon ii = new ImageIcon("Images/3.png");
            live = ii.getImage();
        }
    }


    /**
     * Start gameThread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double refresh = 1000000000 / fps;
        double nextDraw = System.nanoTime() + refresh;

        while (gameThread != null) {
            update();
            repaint();

            // if (lives == 0) {
            //     repaint();
            //     break;
            // }

            try {
                double timeToNext = nextDraw - System.nanoTime();
                timeToNext /= 1000000;
                if (timeToNext < 0) {
                    timeToNext = 0;
                }
                Thread.sleep((long) timeToNext);
                nextDraw += refresh;
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    /**
     * Receive information and update game loop.
     */
    public void update() {
        if (keyInput.isLeft && (foodX - speed) > 0) {
            foodX -= speed;
        }
        if (keyInput.isRight && (foodX + speed) < 536) {
            foodX += speed;
        }
        updateFallEnemies();
    }

    /**
     * Receive information and paint game loop.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        initLive();
        super.paintComponent(graphics);
        graphics.drawImage(cloud.cloud, -100, 0, this);
        fallEnemies.drawFallEnemy(graphics);
        graphics.drawImage(semiColon.semiColon, 520, 0, this);
        graphics.drawImage(heart.heart, 0, 0, this);
        graphics.drawImage(live, 32, 0, this);
        graphics.drawImage(croissant.croissant, foodX, foodY, this);
        if (lives == 0) {
            graphics.drawImage(gameOver.gameOver, 0, 200, this);
        }
        
        graphics.dispose();

    }

    public void updateFallEnemies() {

        for (int i = 0; i < 5; i++) {
            if ((fallEnemies.enemies[i].enemyY + fallEnemies.enemies[i].speed) < 780) {
                fallEnemies.enemies[i].enemyY += fallEnemies.enemies[i].speed;
            }
            if ((fallEnemies.enemies[i].enemyY + 45) >= 810) {
                fallEnemies.enemies[i].resetY();
                fallEnemies.enemies[i].resetX();

            } 
            if ((fallEnemies.enemies[i].enemyY + 50  >= foodY) 
                && (fallEnemies.enemies[i].enemyX >= foodX - 64) 
                && (fallEnemies.enemies[i].enemyX <= foodX + 64)) {
            
                lives -= 1;
                if (lives == 0) {
                    repaint();
                    gameThread = null;
                }
                fallEnemies.enemies[i].resetY();
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }    
}