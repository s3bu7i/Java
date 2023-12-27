package spaceWars;

import java.awt.Color;
import java.awt.Graphics;


public class Health {

    public int health = 100;
    public int bossHealth = 100;

    public void render(GameObjects player, Graphics g) {
        if (this.health > 0) {
            g.setColor(Color.RED);
            g.fillRect(player.getX() + 10, player.getY() + 80, 50, 16);
            g.setColor(Color.BLUE);
            g.fillRect(player.getX() + 10, player.getY() + 80, health / 2, 16);
            g.setColor(Color.WHITE);
            g.drawRect(player.getX() + 10, player.getY() + 80, 50, 16);
        }
    }

    public void renderBoss(GameObjects boss, Graphics g) {
        if (this.health > 0) {
            g.setColor(Color.RED);
            g.fillRect(boss.getX() + 10, boss.getY() + 160, 50, 16);
            g.setColor(Color.GREEN);
            g.fillRect(boss.getX() + 10, boss.getY() + 160, bossHealth / 2, 16);
            g.setColor(Color.WHITE);
            g.drawRect(boss.getX() + 10, boss.getY() + 160, 50, 16);
        }
    }

}
