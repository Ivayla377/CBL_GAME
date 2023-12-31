import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    int fps = 60;
    int stage = 0;
    int score = 0;
    int max = 0;
    int scoreDigit = 0;
    int scoreTenth = 0; 
    int scoreHundreths = 0;
    long minutes = 0;
    long seconds = 0;
    
    Rectangle pseudoRectangle = new Rectangle(150, 200, 288, 216);

    Thread gameThread;
    KeyInput keyInput = new KeyInput();
    MouseClick mouse = new MouseClick();
    Image live;
    Image [] maxScore = new Image[3];
    Player player = new Player();
    Color blueColor = new Color(137, 207, 240);
    Digit printDigit = new Digit();
    Tenth printTenth = new Tenth();
    Hundred printHundred = new Hundred();
    Cloud cloud = new Cloud();
    StartButton startButton = new StartButton();
    //Croissant croissant = new Croissant();
    Heart heart = new Heart();
    //SemiColon semiColon = new SemiColon();
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
        this.addMouseListener(mouse);
        this.setFocusable(true);
        this.setLayout(new FlowLayout());
        initLive();
    }

    public void initLive() {
        loadLive();
    }

    public void initM() {
        loadM();
    }

    private void loadM() {
        ImageIcon ii = new ImageIcon("Images/M.png");
        maxScore[0] = ii.getImage();        
    }

    public void initA() {
        loadA();
    }

    private void loadA() {
        ImageIcon ii = new ImageIcon("Images/A.png");
        maxScore[1] = ii.getImage();        
    }

    public void initX() {
        loadX();
    }

    private void loadX() {
        ImageIcon ii = new ImageIcon("Images/X.png");
        maxScore[2] = ii.getImage();        
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
        if (stage == 0) {
            Enemy.speed = 0;
            graphics.drawImage(startButton.startButton, 150, 200, this);
            drawMaxScore(graphics);
            updateScore(score);
            if (pseudoRectangle.contains(mouse.clickX, mouse.clickY)) {
                Enemy.speed = 4;
                stage += 1;
            }
        }
        fallEnemies.drawFallEnemy(graphics);
        drawScore(graphics);
        //graphics.drawImage(semiColon.semiColon, 520, 0, this);
        graphics.drawImage(heart.heart, 0, 0, this);
        graphics.drawImage(live, 32, 0, this);
        graphics.drawImage(player.player, foodX, foodY, this);
        if (lives == 0) {
            graphics.drawImage(gameOver.gameOver, 0, 200, this);
        }
        
        graphics.dispose();

    }

    public void drawMaxScore(Graphics graphics) {
        initM();
        initA();
        initX();
        int xC = 200;
        for (Image i : maxScore) {
            graphics.drawImage(i, xC, 0, this);
            xC += 32;
        }
        try {
            max = getScoreFromFile();
        } catch (IOException e) {
            System.out.println("nz macka");
        }
        updateScore(max);
        graphics.drawImage(printHundred.hundred, xC + 32, 0, this);
        graphics.drawImage(printTenth.tenth, xC + 64, 0, this);
        graphics.drawImage(printDigit.digit, xC + 96, 0, this);
    }


    public void updateFallEnemies() {

        for (int i = 0; i < 5; i++) {
            if ((fallEnemies.enemies[i].enemyY + Enemy.speed) < 780) {
                fallEnemies.enemies[i].enemyY += Enemy.speed;
            }
            if ((fallEnemies.enemies[i].enemyY + 45) >= 810) {
                fallEnemies.enemies[i].resetY();
                fallEnemies.enemies[i].resetX();
                score++;
                updateScore(score);
            } 
            if ((fallEnemies.enemies[i].enemyY + 50  >= foodY) 
                && (fallEnemies.enemies[i].enemyX >= foodX - 64) 
                && (fallEnemies.enemies[i].enemyX <= foodX + 64)) {
                checkGameOver();
                fallEnemies.enemies[i].resetY();
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

    public int getDigit(int num) {
        return num % 10;
    }

    public int getTenth(int num) {
        return (num % 100) / 10;
    }

    public int getHundreth(int num) {
        return num / 100;
    }

    public void updateScore(int num) {
        scoreDigit = getDigit(num);
        printDigit.digitString = String.valueOf(scoreDigit);
        printDigit.loadDigit();

        scoreTenth = getTenth(num);
        printTenth.tenthString = String.valueOf(scoreTenth);
        printTenth.loadTenth();
        
        scoreHundreths = getHundreth(num);
        printHundred.hundredString = String.valueOf(scoreHundreths);
        printHundred.loadHundred();
        updateStage();
        updatePlayer();
    }

    public void drawScore(Graphics graphics) {
        printDigit.drawDigit(graphics);
        printTenth.drawTenth(graphics);
        printHundred.drawHundred(graphics);
    }

    public void updateStage() {
        if (score == 10 || score == 100) {
            stage++;
            System.out.println(stage);
        }
    }

    public void updatePlayer() {
        if (stage == 2) {
            player.playertoString = "Stroopwaffel";
            player.loadPlayer();
            Enemy.speed = 5;
        }
        if (stage == 3) {
            // player.playertoString = "Stroopwaffel";
            // player.loadPlayer();
            Enemy.speed = 6;
        }
    }
    
    public void checkGameOver() {
        lives -= 1;
        if (lives == 0) {
            repaint();
            gameThread = null;
            saveScore();
        }
    }

    public void saveScore() {
        int maxScoreFromFile = 0;
        try {
            maxScoreFromFile = getScoreFromFile();
        } catch (IOException e) {
            System.out.println("nz macka");
        }
        if (maxScoreFromFile < score) {
            File fold = new File("ScoreText.txt");
            fold.delete();
            File fnew = new File("ScoreText.txt");

            try {
                FileWriter f2 = new FileWriter(fnew, false);
                f2.write(Integer.toString(score));
                f2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }     
        }

    }

    public int getScoreFromFile() throws IOException{
        // Creating a path choosing file from local
        // directory by creating an object of Path class
        Path fileName = 
            Path.of("C:\\Users\\20231442\\OneDrive - TU Eindhoven\\Desktop\\Study Materials\\Programming files\\Java\\Projects and Homework\\Canvas\\Homework\\CBL\\CBL_GAME\\Code\\ScoreText.txt");
 
        // Now calling Files.readString() method to
        // read the file
        String score = Files.readString(fileName);
        // Printing the string
        return Integer.valueOf(score);
    }

    public void drawFinalScore() {
        scoreDigit = score % 10;
        printDigit.digitString = String.valueOf(scoreDigit);
        printDigit.loadDigit();

        scoreTenth = (score % 100) / 10;
        printTenth.tenthString = String.valueOf(scoreTenth);
        printTenth.loadTenth();
        
        scoreHundreths = score / 100;
        printHundred.hundredString = String.valueOf(scoreHundreths);
        printHundred.loadHundred();

    }
}