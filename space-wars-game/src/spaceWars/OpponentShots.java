package spaceWars;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OpponentShots extends GameObjects {

    BufferedImage shot;

    public OpponentShots(int x, int y) {
        super(x, y);
        try {
            shot = ImageIO.read(new File("shotOpponent.png"));

        } catch (IOException e) {
        }
        this.setXVal(-8);
    }


    public void render(Graphics g) {
        g.drawImage(shot, x, y, 20, 20, null);
    }

}
