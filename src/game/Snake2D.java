package game;

import javax.swing.*;

public class Snake2D {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainBoard("Snake2D game", 400, 350);
            }
        });
    }
}
