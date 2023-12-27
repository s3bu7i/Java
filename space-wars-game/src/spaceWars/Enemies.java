package spaceWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Enemies extends GameObjects {

    public BufferedImage opponent;


    public Enemies(int x, int y) {
        super(x, y);
        try {
            opponent = ImageIO.read(new File("opponent.png"));
        } catch (IOException e) {
        }
        this.setXVal(-5);
    }


    public void render(Graphics g) {
        g.drawImage(opponent, x, y, 80, 80, null);
    }

}
