package game;

import javax.swing.*;
import java.awt.*;

public class MainBoard extends JFrame {

    public MainBoard(String title,int width, int height){
        setTitle(title);
//        setSize(new Dimension(width+50, height+50));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setVisible(true);
        createBufferStrategy(2);
        GameBoard g = new GameBoard(width, height, getBufferStrategy());
        add(g);
//        pack();

    }

}
