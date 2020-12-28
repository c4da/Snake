package game;

import java.awt.*;

public class Graphics {

    private int x;
    private int y;
    private int sizePx;
    private Color color;

    public Graphics(int x, int y, int sizePx, Color color){
        setX(x);
        setY(y);
        setSizePx(sizePx);
        setColor(color);
    }

    public void draw(Graphics2D gObject){
        gObject.setColor(color);
        gObject.fillRect(x, y, sizePx, sizePx);
    }

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setSizePx(int sizePx) {this.sizePx = sizePx;}
    public void setColor(Color color) {this.color = color;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getSizePx(){return this.sizePx;}
    public Color getColor(){return this.color;}


}
