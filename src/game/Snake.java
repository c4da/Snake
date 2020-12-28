package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.Direction.*;

public class Snake extends Graphics {
    private final List<Graphics> body;
    private Color colorBody;
    private Direction dir;
    private final Berry berry;

    public ArrayList<Double> dirToBerry;
    public ArrayList<Double> snakeDir;


    public Snake(int x, int y, int sizePx, Berry berry, Color color, Color colorBody) {
        super(x, y, sizePx, color);
        this.body = new ArrayList<>();
        this.berry = berry;
        setColorBody(colorBody);
        setDir(Direction.RIGHT);
        expandBody();
    }

    @Override
    public void draw(Graphics2D gObject) {
        gObject.setColor(getColor());
        gObject.fillRect(getX(), getY(), getSizePx(), getSizePx());

        for (Graphics item:body){
            item.draw(gObject);
        }
    }

    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
    }

    public Color getColorBody(){
        return colorBody;
    }

    public Direction getDir(){
        return this.dir;
    }

    public void setDir(Direction direction){
        this.dir = direction;
    }


    public void expandBody(){
        body.add(0, new Graphics(getX(),getY(), getSizePx(), getColorBody()));
        move();
    }

    public List<Graphics> getBody(){
        return body;
    }

    public void move(){
        moveBody();
        calcAndMoveHead();
    }

    public void calcAndMoveHead(){
        double angle = getAngle(getDirToBerry(), getSnakeDir());
        if (angle<0){setDir(LEFT);}
        if (angle>0){setDir(RIGHT);}
//        if (getSnakeDir() == null){setDir(RIGHT);}

//        Double[] s = getSnakeDir();
//
//        Double[] v1 = getSnakeLeftVector();
//        Double[] v2 = getSnakeRightVector();

        if (getDir() == LEFT){
            moveHead(getSnakeLeftVector());
        }
        if (getDir() == RIGHT){
            moveHead(getSnakeRightVector());
        }

        System.out.println(angle);
        System.out.println(getDir());
    }

    public Double[] getDirToBerry() {
        Double[] v = {0d, 0d};
        int x = getX();
        int y = getY();

        int xBerry = berry.getX();
        int yBerry = berry.getY();

        int dx = xBerry - x;
        int dy = yBerry - y;

        double mag = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        double dx_norm = dx / mag;
        double dy_norm = dy / mag;

        v[0] = dx_norm;
        v[1] = dy_norm;

        return v;

    }

    public double getAngle(Double[] v1, Double[] v2){

        double angle = Math.atan2(v1[1] * v2[0] - v1[0] * v2[1],
                v1[1] * v2[1] + v1[0] * v2[0]) / Math.PI;

        return angle;

    }

//        if ((dx_norm == 0)&(dy_norm == 1)){
//            System.out.println("UP");
//            return UP;
//        }
//        if ((dx_norm == 0)&(dy_norm == -1)){
//            System.out.println("DOWN");
//            return DOWN;
//        }
//        if ((dx_norm == -1)&(dy_norm == 0)){
//            System.out.println("LEFT");
//            return LEFT;
//        }
//        if ((dx_norm == 1)&(dy_norm == 0)){
//            System.out.println("RIGHT");
//            return RIGHT;
//        }
//        return LEFT;
//    }

    public Double[] getSnakeDir(){
        Double[] v = {0d, 0d};

        int x = getX();
        int y = getY();
        System.out.println("body size");
        System.out.println(body.size());

        int x_body = body.get(body.size()-1).getX();
        int y_body = body.get(body.size()-1).getY();

        int dx = x - x_body;
        int dy = y - y_body;

        if ((dx==0)&&(dy==0)){
            v[0] = 1d;
            v[1] = 0d;
            return v;}

        double mag = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));

        double dx_norm = dx/mag;
        double dy_norm = dy/mag;

        v[0] = dx_norm;
        v[1] = dy_norm;

        return v;
    }

    private void moveHead(Double[] vector){

        Double[] left =  {-1d, 0d};
        Double[] right =  {1d, 0d};
        Double[] up =  {0d, 1d};
        Double[] down =  {0d, -1d};

        if (Arrays.equals(vector, left)) {
            setX(getX() - getSizePx());
        } else if ( Arrays.equals(vector,right)) {
            setX(getX() + getSizePx());
        } else if (Arrays.equals(vector, up)) {
            setY(getY() - getSizePx());
        } else if (Arrays.equals(vector, down)){
            setY(getY() + getSizePx());
        }
    }
    private void moveBody(){
        int tmpX = getX(), tmpY = getY(), tmp;

        for (Graphics item : body){
            tmp = item.getX();
            item.setX(tmpX);
            tmpX = tmp;

            tmp = item.getY();
            item.setY(tmpY);
            tmpY = tmp;

        }
    }

    public Double[] getSnakeRightVector(){
        Double[] rightVector = {0d, 0d};
        if (getSnakeDir()[1] != 0){rightVector[0] = getSnakeDir()[1]*(-1);}
        if (getSnakeDir()[0] != 0){rightVector[1] = getSnakeDir()[0];}
        return rightVector;
    }
    public Double[] getSnakeLeftVector(){
        Double[] leftVector = {0d, 0d};
        if (getSnakeDir()[1] != 0){leftVector[0] = getSnakeDir()[1];}
        if (getSnakeDir()[0] != 0){leftVector[1] = getSnakeDir()[0]*(-1);}
        return leftVector;
    }

}
