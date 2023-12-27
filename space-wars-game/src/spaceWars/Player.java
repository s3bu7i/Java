package spaceWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends GameObjects {

    private BufferedImage spaceship;
    private BufferedImage explosion;

    public Player(int x, int y) {
        super(x, y);
        try {
            spaceship = ImageIO.read(new File("spaceship.png"));
            explosion = ImageIO.read(new File("explosion.png"));

        } catch (IOException e) {
        }
    }

    public void render(Graphics g) {
        g.drawImage(spaceship, x, y, 80, 80, null);
    }

    public void renderExploded(Graphics g) {
        g.drawImage(explosion, x, y, 80, 80, null);
    }
}
