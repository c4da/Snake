package game;

public class Collisions {

    private Collisions(){}

    public static boolean checkCollisions(Snake snake, int maxX, int maxY){

        for (Graphics item : snake.getBody()){
            if ((snake.getX() == item.getX())&&(snake.getY() == item.getY())){
                return true;
            }
        }

        if(snake.getX() < GameBoard.getOffset()){return true;}
        if (snake.getY() < GameBoard.getOffset()) {
            return true;
        }
        if(snake.getX() >= maxX){return true;}
        return snake.getY() >= maxY;
    }

    public static boolean checkReward(Snake snake, Berry berry){
        return snake.getX() == berry.getX() && (snake.getY() == berry.getY());
    }

}
