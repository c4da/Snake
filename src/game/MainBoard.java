package game;

import javax.swing.*;
import java.awt.*;

public class MainBoard extends JFrame {

    public MainBoard(String title,int width, int height){
        setTitle(title);
        setSize(new Dimension(width+3, height+3));
        setLocationRelativeTo(null);
        setLocation(0, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createBufferStrategy(2);
        add(new GameBoard(width, height, getBufferStrategy()));
    }

}
