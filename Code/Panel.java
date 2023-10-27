import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
    boolean isGameOver = false;
    int bgX = -100;
    int bgY = 0;
    
    Rectangle pseudoRectangle = new Rectangle(150, 200, 288, 216);

    Thread gameThread;
    Clip backgroundMusic;
    boolean isMusicPlaying = false;
    KeyInput keyInput = new KeyInput();
    MouseClick mouse = new MouseClick();
    Image live;
    Image [] maxScore = new Image[3];
    Player player;
    Color blueColor = new Color(137, 207, 240);
    Digit printDigit = new Digit();
    Tenth printTenth = new Tenth();
    Hundred printHundred = new Hundred();
    Cloud cloud = new Cloud();
    StartButton startButton = new StartButton();
    Heart heart = new Heart();
    GameOver gameOver = new GameOver();
    Random rand = new Random(); 
    EnemiesManager enemiesManager;

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
        this.enemiesManager = new EnemiesManager();
        player = new Player();
        initLive();
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
            if (stage != 0){
                update();
            }
            repaint();

            try {
                double timeToNext = nextDraw - System.nanoTime();
                timeToNext /= 1000000;
                if (timeToNext < 0) {
                    timeToNext = 0;
                }
                Thread.sleep((long) timeToNext);
                nextDraw += refresh;
            } catch (Exception e) {
                System.out.println("Error in Thread");
            }

        }
    }

    /**
     * Receive information and update game loop.
     */
    public void update() {
        player.updatePlayer(keyInput);        
        updateFallEnemies();
        updateStage();
        checkRestart();

        if (keyInput.isLeft && bgX < 0) {
            bgX += 1;
        }
        if (keyInput.isRight && bgX + 780 > 600) {
            bgX -= 1;
        }
    }

    /**
     * Seperate thread that deals with audio.
     */
    public void startBackgroundMusic() {
        if (!isMusicPlaying) {
            Thread musicThread = new Thread(() -> {
                try {
                    InputStream inputStream = 
                        getClass().getClassLoader().getResourceAsStream("Media/bumperCar.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
                    AudioFormat audioFormat = audioStream.getFormat();

                    DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
                    backgroundMusic = (Clip) AudioSystem.getLine(info);
                    backgroundMusic.open(audioStream);
                    backgroundMusic.start();
                    isMusicPlaying = true;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            musicThread.start();
        }
    }

    /**
     * Determine when the audio is stopped a.k.a. the thread is set to null.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.close();
            isMusicPlaying = false;
        }
    }

    /**
     * Receive information and paint game loop.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        initLive();
        super.paintComponent(graphics);
        graphics.drawImage(cloud.cloud, bgX, bgY, this);


        if (stage == 0) {
            graphics.drawImage(startButton.startButton, 150, 200, this);
            drawMaxScore(graphics);
            updateScore(score);
            if (pseudoRectangle.contains(mouse.clickX, mouse.clickY)) {
                mouse.clickX = 0;
                mouse.clickY = 0;
                stage = 1;
                // startMusic();
                // audioThread.start();
                startBackgroundMusic();

                enemiesManager.initStage1Enemies(5);
            }
        }


        enemiesManager.drawEnemies(graphics);

        drawScore(graphics);
        graphics.drawImage(heart.heart, 0, 0, this);
        graphics.drawImage(live, 32, 0, this);
        graphics.drawImage(player.player, player.foodX, player.foodY, this);

        if (lives == 0) {
            graphics.drawImage(gameOver.gameOver, 0, 200, this);
            graphics.drawImage(gameOver.restartText, 150, 100, this);
        }

        if (isGameOver) {
            enemiesManager.clearEnemies();
        }

        graphics.dispose();

    }

    /**
     * Method that implements all methods used to determine changes in enemies.
     */
    public void updateFallEnemies() {
        for (Enemy enemy : enemiesManager.enemies) { 
            enemy.moveEnemy();
            if (enemy.checkForCollision(player.foodX, player.foodY)) {
                enemiesManager.resetEnemyPosition(enemy);
                checkGameOver();
            }
            if ((enemy.enemyY + 45) >= 810) {
                enemiesManager.resetEnemyPosition(enemy);
                score++;
                updateScore(score);
            } 
        }
    }

    /**
     * Method used to detect conditions needed to switch between each stage.
     */
    public void updateStage() {

        if (score == 20 && stage == 1) {
            stage = 2;
            enemiesManager.initStage2Enemies(6);
            player.playertoString = "Stroopwaffel";
            player.playertoString = "pancakes";
            player.loadPlayer();
            System.out.println(stage);
        }

        if (score == 50 && stage == 2) {
            stage = 3;
            enemiesManager.initStage3Enemies(6);
            player.playertoString = "Taco";
            player.loadPlayer();
            System.out.println(stage);
        }

        if (score == 100 && stage == 3) {
            stage = 4;
            enemiesManager.initStage4Enemies(6);
            player.playertoString = "Taco";
            player.loadPlayer();
            System.out.println(stage);
        }
    }

    /**
     * Check when to paint Game Over screen.
     */
    public void checkGameOver() {
        lives -= 1;
        if (lives == 0) {
            repaint();
            stopBackgroundMusic();
            isGameOver = true;
            saveMaxScore();
        }
    }

    /**
     * Check is the r-key is pressed for restart.
     */
    public void checkRestart() {
        if (isGameOver && keyInput.restart) {
            lives = 3;
            score = 0;
            stage = 0;
            isGameOver = false;
            player.playertoString = "Croissant";
            player.loadPlayer();
            repaint();
        }
        
    }

    /**
     * Method used to keep track of score.
     */
    public void saveMaxScore() {
        int maxScoreFromFile = 0;
        try {
            maxScoreFromFile = getScoreFromFile();
        } catch (IOException e) {
            System.out.println("Error in score retrieval");
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
    
    /**
     * Display score in panel and update accordingly.
     * @param num Score
     */
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
    }

    /**
     * Draw score on panel.
     * @param graphics Graphics used to draw
     */
    public void drawScore(Graphics graphics) {
        printDigit.drawDigit(graphics);
        printTenth.drawTenth(graphics);
        printHundred.drawHundred(graphics);
    }

    /**
     * Draw score in panel before game is started.
     * @param graphics Graphics used to draw
     */
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
            System.out.println("Error at score retrieval");
        }
        updateScore(max);
        graphics.drawImage(printHundred.hundred, xC + 32, 0, this);
        graphics.drawImage(printTenth.tenth, xC + 64, 0, this);
        graphics.drawImage(printDigit.digit, xC + 96, 0, this);
    }

    /**
     * Draw score after game is over.
     */
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

    /**
     * Method that reads score from txt file.
     * @return Score as int
     * @throws IOException If such a file does not exist
     */
    public int getScoreFromFile() throws IOException {
        Path fileName = Path.of("ScoreText.txt");

        String score = Files.readString(fileName);
        return Integer.valueOf(score);
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

    public int getDigit(int num) {
        return num % 10;
    }

    public int getTenth(int num) {
        return (num % 100) / 10;
    }

    public int getHundreth(int num) {
        return num / 100;
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

}