package spaceWars;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;


public class Actions implements ActionListener {

    public GameObjects player;

    public boolean[] BossAlive = { false, false, false };
    private GameObjects[] bosses = { null, null, null };
    private Random r = new Random();
    private int[] howManyBossShots = { 0, 0, 0 };
    private Health healthPanel;
    private Gameplay gameplay;
    private int score = 0;
    private int howManyOpponentShots = 0;

    private LinkedList<GameObjects> opponentShots = new LinkedList<GameObjects>();
    private LinkedList<GameObjects> playerShots = new LinkedList<GameObjects>();
    private LinkedList<GameObjects> opponents = new LinkedList<GameObjects>();
    private LinkedList<GameObjects> bossShots = new LinkedList<GameObjects>();



    public Actions(Health healthPanel, GameObjects player, Gameplay gameplay) {
        this.healthPanel = healthPanel;
        this.player = player;
        this.gameplay = gameplay;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void clearObjects() {
        this.opponentShots.clear();
        this.playerShots.clear();
        this.opponents.clear();
        this.bossShots.clear();
    }

    public void addPlayerShot(GameObjects object) {
        this.playerShots.add(object);
    }


    public void addOpponentShot(GameObjects object) {
        this.opponentShots.add(object);
    }


    public void addOpponents(GameObjects object) {
        this.opponents.add(object);
    }

    public void addBossShot(GameObjects object) {
        this.bossShots.add(object);
    }


    public void movePlayerShots() {
        for (int i = 0; i < playerShots.size(); i++) {
            playerShots.get(i).movementX();
        }
    }

    public void moveOpponents() {
        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).movementX();
            if (opponents.get(i).x < 0)
                opponents.remove(opponents.get(i));
        }
    }

    public void makeOpponentShots(int delay) {
        howManyOpponentShots++;
        if (howManyOpponentShots % delay == 0) {
            for (int i = 0; i < opponents.size(); i++) {
                addOpponentShot(new OpponentShots(opponents.get(i).getX(), opponents.get(i).getY() + 30));
            }
        }
    }


    public void moveOpponentShots() {
        for (int i = 0; i < opponentShots.size(); i++) {
            opponentShots.get(i).movementX();
        }
    }


    public void makeOpponent() {
        addOpponents(new Enemies(gameplay.getWidth(), r.nextInt(525)));
    }


    public void makeBoss() {
        for (int i = 0; i < 3; i++) {
            bosses[i] = new Boss(1000, 100);
        }
    }



    public void moveBoss(int level) {

        if (bosses[level - 1].getX() >= 500) {
            bosses[level - 1].movementX();
        }
        bosses[level - 1].movementY();

    }


    public void makeBossShots(int level, int delay) {

        howManyBossShots[level - 1]++;
        if (howManyBossShots[level - 1] > 300) {
            if (howManyBossShots[level - 1] % delay == 0) {
                addBossShot(new BossShots(bosses[level - 1].getX() + 20, bosses[level - 1].getY() + 30));
            }
        }

    }

    public void moveBossShots(int level) {
        for (int i = 0; i < bossShots.size(); i++) {
            bossShots.get(i).movementX();
            if (bossShots.get(i).getX() < 0) {
                bossShots.remove(bossShots.get(i));
            }
        }
    }

    @param level

    public void playerShotTouchedOpponent(int level) {
        Rectangle playerShot = new Rectangle();
        Rectangle opponentRect = new Rectangle();
        Rectangle bossRect = new Rectangle(bosses[level - 1].getX(), bosses[level - 1].getY(), 250, 165);
        for (int i = 0; i < playerShots.size(); i++) {
            playerShot = new Rectangle(playerShots.get(i).x, playerShots.get(i).y, 30, 30);
            for (int j = 0; j < opponents.size(); j++) {
                opponentRect = new Rectangle(opponents.get(j).x, opponents.get(j).y, 80, 80);
                if (playerShot.intersects(opponentRect)) {
                    opponents.remove(opponents.get(j));
                    playerShots.remove(playerShots.get(i));
                    this.setScore(this.getScore() + 50);
                }
            }
            if (playerShot.intersects(bossRect)) {
                if (bosses[level - 1].getX() <= 530) {
                    healthPanel.bossHealth -= 5;
                    playerShots.remove(playerShots.get(i));
                    this.setScore(this.getScore() + 100);
                }
            }
        }

    }


    public void opponentShotTouchedPlayer() {
        Rectangle playerRect = new Rectangle(this.player.getX(), this.player.getY(), 80, 80);
        Rectangle opponentShot = new Rectangle();
        Rectangle bossShot = new Rectangle();
        for (int i = 0; i < opponentShots.size(); i++) {
            opponentShot = new Rectangle(opponentShots.get(i).x, opponentShots.get(i).y, 20, 20);
            if (playerRect.intersects(opponentShot)) {
                opponentShots.remove(opponentShots.get(i));
                healthPanel.health -= 5;
            }
        }

        for (int k = 0; k < bossShots.size(); k++) {
            bossShot = new Rectangle(bossShots.get(k).x, bossShots.get(k).y, 50, 50);
            if (bossShot.intersects(playerRect)) {
                bossShots.remove(bossShots.get(k));
                healthPanel.health -= 10;
            }
        }

    }


    public void playerTouchedOpponent(int level) {
        Rectangle playerRect = new Rectangle(this.player.getX(), this.player.getY(), 80, 80);
        Rectangle opponentRect = new Rectangle();
        Rectangle bossRect = new Rectangle(this.bosses[level - 1].getX(), this.bosses[level - 1].getY(), 250, 165);
        for (int i = 0; i < opponents.size(); i++) {
            opponentRect = new Rectangle(opponents.get(i).x, opponents.get(i).y, 80, 80);
            if (playerRect.intersects(opponentRect)) {
                healthPanel.health -= 25;
                opponents.remove(opponents.get(i));
            }
        }
        if (playerRect.intersects(bossRect)) {
            healthPanel.health -= 50;
        }
    }

    public void didShotsIntersect() {
        Rectangle playerShot = new Rectangle();
        Rectangle opponentShot = new Rectangle();
        Rectangle bossShot = new Rectangle();

        for (int i = 0; i < playerShots.size(); i++) {
            playerShot = new Rectangle(playerShots.get(i).x, playerShots.get(i).y, 30, 30);
            for (int j = 0; j < opponentShots.size(); j++) {
                opponentShot = new Rectangle(opponentShots.get(j).x, opponentShots.get(j).y, 20, 20);
                if (opponentShot.intersects(playerShot)) {
                    opponentShots.remove(opponentShots.get(j));
                    playerShots.remove(playerShots.get(i));
                    this.setScore(this.getScore() + 10);
                }
            }
            for (int k = 0; k < bossShots.size(); k++) {
                bossShot = new Rectangle(bossShots.get(k).x, bossShots.get(k).y, 50, 50);
                if (bossShot.intersects(playerShot)) {
                    bossShots.remove(bossShots.get(k));
                    playerShots.remove(playerShots.get(i));
                    this.setScore(this.getScore() + 30);
                }
            }
        }
    }


    public void speedTheBossUp(int level) {
        bosses[level - 1].setYVal(bosses[level - 1].getYVal() + 1);
    }

    public boolean checkIfBossIsAlive() {
        if (healthPanel.bossHealth > 0) {
            return true;
        } else
            return false;
    }

    public boolean checkIfPlayerIsAlive() {
        if (healthPanel.health > 0) {
            return true;
        } else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }


    public void render(Graphics g) {

        if (checkIfPlayerIsAlive()) {
            player.render(g);
        } else {
            player.renderExploded(g);
        }

        for (int i = 0; i < playerShots.size(); i++) {
            playerShots.get(i).render(g);
        }

        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).render(g);
        }

        for (int i = 0; i < opponentShots.size(); i++) {
            opponentShots.get(i).render(g);
        }

        for (int i = 0; i < bossShots.size(); i++) {
            bossShots.get(i).render(g);
        }

        if (BossAlive[0]) {
            bosses[0].render(g, 1);
            healthPanel.renderBoss(bosses[0], g);
        }

        if (BossAlive[1]) {
            bosses[1].render(g, 2);
            healthPanel.renderBoss(bosses[1], g);
        }

        if (BossAlive[2]) {
            bosses[2].render(g, 3);
            healthPanel.renderBoss(bosses[2], g);
        }

    }

}
