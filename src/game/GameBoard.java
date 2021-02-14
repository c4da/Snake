package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class GameBoard extends JPanel implements Runnable {

    private final int WIDTH;
    private final int HEIGHT;
    private final int gameNumber;
    private Snake snake;
    private Berry berry;
    private boolean inGame;
    private final BufferStrategy bs;
    private final int FRAME_DELAY = 200;
    private long cycleTime;
    private static int offset = 50;
    public ScreenMemory gameMemory;

    public GameBoard(int gameNumber, int width, int height, BufferStrategy bs) {
        this.gameNumber = gameNumber;
//        addKeyListener(new TAdapter());
        setFocusable(true);
        setIgnoreRepaint(true);
        WIDTH = width-2*offset;
        HEIGHT = height-2*offset;
        gameMemory = new ScreenMemory(WIDTH, HEIGHT, 10);
        this.bs = bs;
//        setBounds(offset, offset, WIDTH, HEIGHT);
        setSize(new Dimension(WIDTH, HEIGHT));

        gameInit();
    }

    public static int getOffset() {
        return offset;
    }

    private void gameInit() {
        inGame = true;
        berry = new Berry(10, Color.RED, WIDTH, HEIGHT);
        berry.locateBerry();

        snake = new Snake((WIDTH /20)*10, (HEIGHT /20)*10, 10, berry, Color.GREEN, Color.GRAY);

        Thread animation = new Thread(this, "Game");
        animation.start();

    }

    @Override
    public void run() {

        cycleTime = System.currentTimeMillis();

        while(inGame){
            gameMemory.saveScreen(snake.getBody(), berry);
            gameMemory.saveGameData(snake.getBody(), berry);
            updateLogic();
            updateGui();
            synchFrameRate();
        }

        try {
            gameMemory.saveFile(gameNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOver();
    }

    /**
     * synchronizace FPS
     */
    private void synchFrameRate() {
        cycleTime += FRAME_DELAY;
        long difference = cycleTime-System.currentTimeMillis();
        try {
            Thread.sleep(Math.max(0, difference));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cycleTime = System.currentTimeMillis();
    }

    /**
     * boardgame draw
     */
    private void updateGui() {
        Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

        g2.setColor(Color.BLACK);

//        g2.fillRect((int) getLocation().getX(), (int) getLocation().getY(), WIDTH, HEIGHT);
        g2.fillRect(offset, offset, WIDTH, HEIGHT);
        snake.draw(g2);
        berry.draw(g2);

        bs.show();

        Toolkit.getDefaultToolkit().sync();
    }

    private void updateLogic() {
//        System.out.println("checking");
        if(Collisions.checkCollisions(snake, WIDTH, HEIGHT)){
            inGame=false;
        }else if(Collisions.checkReward(snake, berry)){
//            System.out.println(true);
            berry.locateBerry();
            snake.expandBody();
        }else{
//            snake.getSnakeDir();
//            snake.calcAndMoveHead();
            snake.move();
//            snake.getDirToBerry();
        }
    }

    private void gameOver(){
        Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

        String message = "you loose!";
        String score = "Score: " + snake.getBody().size();
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(font);

        g2.setColor(Color.BLACK);
        g2.fillRect(offset, offset, WIDTH, HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString(message, (WIDTH+offset-metr.stringWidth(message))/2, ((HEIGHT+offset)/2)+25);
        g2.drawString(score, (WIDTH+offset-metr.stringWidth(score))/2, ((HEIGHT+offset)/2)-25);

        g2.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
