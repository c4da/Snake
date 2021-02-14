package game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScreenMemory implements Serializable {

    public short memSize;
    public int width;
    public int height;
    public int size;

    public float[][] oneScreen;
    public ArrayList<float[][]> game;

    public ScreenMemory(int width, int height, int size){
        this.game = new ArrayList<float[][]>();
        this.width = width;
        this.height = height;
        this.size = size;
        this.oneScreen = new float[Math.round(width/size)+10][Math.round(height/size)+10];
        initScreenMemory();
    }

    public void initScreenMemory(){

        for (float[] row: oneScreen)
            Arrays.fill(row, 0.0f);
    }

    public void saveScreen(List<Graphics> snakeBody, Graphics berry){

        initScreenMemory();
        //offset = 100 -> proto -10
        for (Graphics pos:snakeBody){
            int posX = Math.round(pos.getX()/size);
            int posY = Math.round(pos.getY()/size);
            oneScreen[posX][posY] = 1;
        }
        int berryX = Math.round(berry.getX()/size);
        int berryY = Math.round(berry.getY()/size);
        oneScreen[berryX][berryY] = 10;
    }

    public void saveGameData(List<Graphics> snakeBody, Graphics berry){
        saveScreen(snakeBody, berry);
        game.add(oneScreen);
    }

    public void saveFile(int gameNumber) throws IOException {
        String game = Integer.toString(gameNumber)+"_game";
        String filepath = "C:\\cada\\JavaProgramms\\Snake\\"+game;
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(game);
        objectOut.close();
    }

}
