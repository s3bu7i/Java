package spaceWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Boss extends GameObjects {

    private BufferedImage boss;
    private BufferedImage boss1;
    private BufferedImage boss2;


    public Boss(int x, int y) {
        super(x, y);
        try {
            boss = ImageIO.read(new File("boss.png"));
            boss1 = ImageIO.read(new File("boss1.png"));
            boss2 = ImageIO.read(new File("boss2.png"));
        } catch (IOException e) {
        }
        this.setYVal(1);
        this.setXVal(-1);
    }

    @Override
    public void movementY() {
        this.setY(this.getY() + this.getYVal());
        if (this.getY() > 400 || this.getY() < 0) {
            this.setYVal(-this.getYVal());
        }
    }


    public void render(Graphics g, int ID) {
        if (ID == 1) {
            g.drawImage(boss, x, y, 250, 165, null);
        } else if (ID == 2) {
            g.drawImage(boss1, x, y, 250, 165, null);
        } else {
            g.drawImage(boss2, x, y, 250, 165, null);
        }

    }

}
