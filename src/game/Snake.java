package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        initExpandBody();
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
        body.add(0, new Graphics(getX(), getY(), getSizePx(), getColorBody()));
        calcAndMoveHead();
//        move();
    }

    public void initExpandBody(){
//        body.add(0, new Graphics(getX(),getY(), getSizePx(), getColor()));
        body.add(0, new Graphics(getX(),getY()+getSizePx(), getSizePx(), getColorBody()));
    }

    public List<Graphics> getBody(){
        return body;
    }

    public void move(){
//        moveBody();
        calcAndMoveHead();
    }

    public void calcAndMoveHead(){
        double angle = getAngle(getDirToBerry(), getSnakeDir());
        boolean clear = false;
        while (!clear) {
            if (angle == 0 || angle == -1 || angle == 1) {
                Integer[] currentDir = getSnakeDir();
                currentDir[1] = currentDir[1] * (-1);
                if (!checkSelfCollision(currentDir)) {
                    moveHead(currentDir);
                    break;
                }

            } else if (angle > 0) {
                setDir(RIGHT);
                if (!checkSelfCollision(getSnakeRightVector())) {
                    moveHead(getSnakeRightVector());
                    break;
                }
//            moveHead(getSnakeRightVector());
            } else if (angle < 0) {
                setDir(LEFT);
                if (!checkSelfCollision(getSnakeLeftVector())) {
                    moveHead(getSnakeLeftVector());
                    break;
                }
//            moveHead(getSnakeLeftVector());
            }
            Random rand = new Random();
            angle = Math.sin(rand.nextDouble()*Math.PI*rand.nextInt(10));
        }
    }

    public Double[] getDirToBerry() {
        Double[] v = {0d, 0d};
        int x = getX();
        int y = getY();

        int xBerry = berry.getX();
        int yBerry = berry.getY();

        int dx = x - xBerry ;
        int dy = y - yBerry ;

        double mag = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        double dx_norm = dx / mag;
        double dy_norm = dy / mag;

        v[0] = dx_norm;
        v[1] = dy_norm;

        return v;

    }

    public double getAngle(Double[] v1, Integer[] v2){

        double angle = Math.atan2(v1[1] * v2[0] - v1[0] * v2[1],
                v1[1] * v2[1] + v1[0] * v2[0]) / Math.PI;

        return angle;

    }

    public boolean checkSelfCollision(Integer[] direction){
//        Integer[] collisions = {0, 0, 0};
        int dirChangeX = direction[0]*getSizePx();
        int dirChangeY = direction[1]*getSizePx();
        int newX = getX()+dirChangeX;
        int newY = getY()+dirChangeY;

        for (Graphics item : getBody()) {
            if (newX == item.getX() && newY == item.getY()){
                return true;
            }
        }
        return false;
    }

    public Integer[] getSnakeDir(){
        Integer[] v = {0, 0};

        int x = getX();
        int y = getY();
        System.out.println("body size");
        System.out.println(body.size());

        int x_body = body.get(0).getX();
        int y_body = body.get(0).getY();

        int dx = x - x_body;
        int dy = y - y_body;

        if ((dx==0)&&(dy==0)){
            v[0] = 1;
            v[1] = 0;
            return v;}

        double mag = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));

        double dx_norm = dx/mag;
        double dy_norm = dy/mag;

        v[0] = (int) dx_norm;
        v[1] = (int) dy_norm * (-1);

        return v;
    }

    private void moveHead(Integer[] vector){

        Integer[] left =  {-1, 0};
        Integer[] right =  {1, 0};
        Integer[] down =  {0, 1};
        Integer[] up =  {0, -1};

        int tmpX = getX(), tmpY = getY(), tmp;

        for (Graphics item : body){
            tmp = item.getX();
            item.setX(tmpX);
            tmpX = tmp;

            tmp = item.getY();
            item.setY(tmpY);
            tmpY = tmp;

        }

        if ((vector[0] == left[0])&&(vector[1] == left[1])) {
            setX(getX() - getSizePx());
        } else if ((vector[0] == right[0])&&(vector[1] == right[1])) {
            setX(getX() + getSizePx());
        } else if ((vector[0] == up[0])&&(vector[1] == up[1])) {
            setY(getY() - getSizePx());
        } else if ((vector[0] == down[0])&&(vector[1] == down[1])){
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

//    left_direction_vector = np.array([snake_dir[1]*(-1), snake_dir[0]])
//    right_direction_vector = np.array([snake_dir[1], snake_dir[0]*(-1)])

    public Integer[] getSnakeRightVector(){
        Integer[] rightVector = {0, 0};
//        if (getSnakeDir()[0] != 0){rightVector[1] = getSnakeDir()[0];}
//        if (getSnakeDir()[1] != 0){rightVector[0] = getSnakeDir()[1]*(-1);}
//        rightVector[0] = getSnakeDir()[1];
//        rightVector[1] = getSnakeDir()[0]*(-1);

        Integer[] snakeDir = getSnakeDir();

        rightVector[0] = snakeDir[1];
        rightVector[1] = snakeDir[0]*(-1);
        return rightVector;
    }
    public Integer[] getSnakeLeftVector(){
        Integer[] leftVector = {0, 0};
//        if (getSnakeDir()[0] != 0){leftVector[1] = getSnakeDir()[1]*(-1);}
//        if (getSnakeDir()[1] != 0){leftVector[0] = getSnakeDir()[0];}
//        leftVector[0] = getSnakeDir()[1]*(-1);
//        leftVector[1] = getSnakeDir()[0];
        Integer[] snakeDir = getSnakeDir();
        leftVector[0] = snakeDir[1]*(-1);
        leftVector[1] = snakeDir[0];

        return leftVector;
    }
}
