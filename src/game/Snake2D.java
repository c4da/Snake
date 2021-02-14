package game;

import javax.swing.*;

public class Snake2D {

    public static void main(String[] args){
        for (int i = 0; i<1; ++i) {
            int finalI = i;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MainBoard("Snake2D game", 400, 350, finalI);
                }
            });
        }
    }
}
