package spaceWars;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

    public Window(int width, int height, String title, Gameplay gameplay) {
        JFrame jframe = new JFrame();
        jframe.setPreferredSize(new Dimension(width, height));
        jframe.setMaximumSize(new Dimension(width, height));
        jframe.setMinimumSize(new Dimension(width, height));
        jframe.setTitle(title);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.add(gameplay);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
