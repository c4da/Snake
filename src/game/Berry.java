package game;

import java.awt.*;
import java.util.Random;

public class Berry extends Graphics{

    private final int maxX;
    private final int maxY;
    private final Random rand;
    private int x;
    private int y;


    public Berry(int sizePx, Color color, int maxX, int maxY) {
        super(0, 0, sizePx, color);
        this.maxX = maxX - 20;
        this.maxY = maxY - 20;
        rand = new Random();
    }

    /**
     * maxX - game board width in px
     * getSizePx() returns size of the object (berry) in px
     * maxX/getSizePx() gets the number of objects that fits into the board
     * rand.nextInt gets a rondom int in a bound from zero to argument
     *
     */
    public void locateBerry(){
        int tmp;
        tmp = rand.nextInt((maxX-getSizePx())/getSizePx());
        x = tmp*getSizePx();
        setX(x);

        tmp = rand.nextInt((maxY-50-getSizePx())/getSizePx());
        y = tmp*getSizePx();
        setY(y);
    }

    public int getX() {return x; }
    public int getY(){
        return y;
    }



}
