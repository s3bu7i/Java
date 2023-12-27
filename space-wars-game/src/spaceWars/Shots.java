package spaceWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shots extends GameObjects {

    private BufferedImage shot;

    public Shots(int x, int y) {
        super(x, y);
        try {
            shot = ImageIO.read(new File("shot.png"));

        } catch (IOException e) {
        }
        this.setXVal(10);
    }
    public void render(Graphics g) {
        g.drawImage(shot, x, y, 30, 30, null);
    }

}
