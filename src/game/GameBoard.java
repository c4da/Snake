package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class GameBoard extends JPanel implements Runnable {

    private final int WIDTH;
    private final int HEIGHT;
    private Snake snake;
    private Berry berry;
    private boolean inGame;
    private final BufferStrategy bs;
    private final int FRAME_DELAY = 400;
    private long cycleTime;

    public GameBoard(int width, int height, BufferStrategy bs) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setIgnoreRepaint(true);
        WIDTH = width;
        HEIGHT = height;
        this.bs = bs;

        gameInit();
    }

    private void gameInit() {
        inGame = true;

        berry = new Berry(10, Color.YELLOW, WIDTH, HEIGHT);
        berry.locateBerry();

        snake = new Snake(50, 50, 10, berry, Color.GREEN, Color.GRAY);

        Thread animation = new Thread(this, "Game");
        animation.start();

    }

    @Override
    public void run() {

        cycleTime = System.currentTimeMillis();

        while(inGame){
            updateLogic();
            updateGui();
            synchFrameRate();
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
        g2.fillRect(0,0, WIDTH, HEIGHT);

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
            System.out.println(true);
            snake.expandBody();
            berry.locateBerry();
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
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString(message, (WIDTH-metr.stringWidth(message))/2, (HEIGHT/2)+25);
        g2.drawString(score, (WIDTH-metr.stringWidth(score))/2, (HEIGHT/2)-25);

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


    /**
     * takes care of key events
     */
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            if ((key == KeyEvent.VK_UP || key==KeyEvent.VK_W) && (snake.getDir()!=Direction.DOWN)) {
                System.out.println("up");
                snake.setDir(Direction.UP);
            }
            if ((key == KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) && (snake.getDir()!=Direction.LEFT)) {
                System.out.println("right");
                snake.setDir(Direction.RIGHT);
            }
            if ((key == KeyEvent.VK_DOWN || key==KeyEvent.VK_S) && (snake.getDir()!=Direction.UP)) {
                System.out.println("down");
                snake.setDir(Direction.DOWN);
            }
            if ((key == KeyEvent.VK_LEFT || key==KeyEvent.VK_A) && (snake.getDir()!=Direction.RIGHT)) {
                System.out.println("left");
                snake.setDir(Direction.LEFT);
            }

        }

    }
}
