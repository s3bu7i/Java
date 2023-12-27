package spaceWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BossShots extends GameObjects {

    private BufferedImage bossShot;



    public BossShots(int x, int y) {
        super(x, y);
        try {
            bossShot = ImageIO.read(new File("bossShot.png"));

        } catch (IOException e) {
        }
        this.setXVal(-3);
    }

    public void render(Graphics g) {
        g.drawImage(bossShot, x, y, 50, 50, null);
    }

}
