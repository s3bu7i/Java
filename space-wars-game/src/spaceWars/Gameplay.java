package spaceWars;

import java.awt.Image;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean opponentsTime = true;
    private boolean isOver = true;
    private boolean keylock = false;
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private Image image;
    private Health healthPanel;
    private GameObjects player;
    private Actions actions;
    private File music = new File("music.wav");

    private ImageIcon bg = new ImageIcon("background.jpg");
    private ImageIcon bg1 = new ImageIcon("background1.jpg");
    private ImageIcon bg2 = new ImageIcon("background2.jpg");
    private ImageIcon gameOverBg = new ImageIcon("gameover.jpg");
    private ImageIcon youAreWinner = new ImageIcon("youarewinner.png");
    private ImageIcon menu = new ImageIcon("menu.jpg");
    private ImageIcon instructions = new ImageIcon("instructions.jpg");

    private int howManyOpponents = 0;
    private int givenShots = 0;
    private int givenShotsTimer = 0;
    private int choice = 0;
    private int gameOver = 0;
    private int level = 1;
    private int closeTheGame = 0;
    private int[] nextLevel = { 0, 0, 0 };


   
    public Gameplay() {
        player = new Player(35, 260);
        new Window(800, 600, "SpaceWars - Konrad Zadroga", this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        healthPanel = new Health();
        actions = new Actions(healthPanel, player, this);
        actions.makeBoss();
        timer = new Timer(10, this);
        timer.start();
        PlaySound(music);
    }

    public static void main(String[] args) {
        new Gameplay();
    }

    private static void PlaySound(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {

        }
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (choice == 0) {
            image = menu.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
        } else if (choice == 1) {
            image = bg.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
            actions.render(g);
            healthPanel.render(player, g);
            g.setFont(new Font("Bodoni MT", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("LEVEL 1", 350, 25);
            g.drawString("SCORE: " + actions.getScore(), 600, 25);

        } else if (choice == 2) {
            image = instructions.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
        } else if (choice == 3) {
            image = bg1.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
            actions.render(g);
            healthPanel.render(player, g);
            g.setFont(new Font("Bodoni MT", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("LEVEL 2", 350, 25);
            g.drawString("SCORE: " + actions.getScore(), 600, 25);
        } else if (choice == 4) {
            image = bg2.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
            actions.render(g);
            healthPanel.render(player, g);
            g.setFont(new Font("Bodoni MT", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("LEVEL 3", 350, 25);
            g.drawString("SCORE: " + actions.getScore(), 600, 25);
        } else if (choice == 5) {
            closeTheGame++;
            image = gameOverBg.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
            g.setFont(new Font("Helvetica", Font.BOLD, 36));
            g.setColor(Color.WHITE);
            g.drawString("Unfortunately you have lost.", getWidth() / 2 - 230, getHeight() / 2 - 50);
            g.drawString("You scored " + actions.getScore() + " points.", getWidth() / 2 - 230, getHeight() / 2);
            if (closeTheGame >= 500) {
                System.exit(1);
            }
        } else if (choice == 6) {
            closeTheGame++;
            image = youAreWinner.getImage();
            g.drawImage(image, 1, 1, 800, 600, this);
            g.setFont(new Font("Helvetica", Font.BOLD, 36));
            g.setColor(Color.WHITE);
            g.drawString("Congratulations!!! You have WON!!!", getWidth() / 2 - 280, getHeight() / 2 - 50);
            g.drawString("You scored " + actions.getScore() + " points. Great score!!", getWidth() / 2 - 300,
                    getHeight() / 2);
            if (closeTheGame >= 500) {
                System.exit(1);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        repaint();

        if (!isOver) {
            givenShotsTimer++;
            if (givenShotsTimer % 300 == 0) {
                givenShots = 0;
            }

            if (choice == 1) {
                howManyOpponents++;
                if (howManyOpponents % 40 == 0 && opponentsTime) {
                    actions.makeOpponent();
                    actions.makeOpponentShots(6);
                }
                if (howManyOpponents >= 1500) {
                    opponentsTime = false;
                    actions.BossAlive[level - 1] = true;
                }

                if (actions.BossAlive[level - 1]) {
                    actions.moveBoss(level);
                    actions.makeBossShots(level, 60);
                    actions.moveBossShots(level);
                    if (!actions.checkIfBossIsAlive()) {
                        actions.BossAlive[level - 1] = false;
                    }
                }

                if (!actions.checkIfBossIsAlive() && !actions.BossAlive[level - 1]) {
                    nextLevel[level - 1]++;
                    actions.clearObjects();
                    if (nextLevel[level - 1] == 300) {
                        howManyOpponents = 0;
                        opponentsTime = true;
                        healthPanel.health = 100;
                        healthPanel.bossHealth = 100;
                        choice = 3;
                        level = 2;
                        actions.speedTheBossUp(level);
                    }
                }
            }

            // level 2
            else if (choice == 3) {
                howManyOpponents++;
                if (howManyOpponents % 25 == 0 && opponentsTime) {
                    actions.makeOpponent();
                    actions.makeOpponentShots(5);
                }
                if (howManyOpponents >= 1800) {
                    opponentsTime = false;
                    actions.BossAlive[level - 1] = true;
                }

                if (actions.BossAlive[level - 1]) {
                    actions.moveBoss(level);
                    actions.makeBossShots(level, 45);
                    actions.moveBossShots(level);
                    if (!actions.checkIfBossIsAlive()) {
                        actions.BossAlive[level - 1] = false;
                    }
                }

                if (!actions.checkIfBossIsAlive() && !actions.BossAlive[level - 1]) {
                    nextLevel[level - 1]++;
                    actions.clearObjects();
                    if (nextLevel[level - 1] == 300) {
                        howManyOpponents = 0;
                        opponentsTime = true;
                        healthPanel.health = 100;
                        healthPanel.bossHealth = 100;
                        choice = 4;
                        level = 3;
                        actions.speedTheBossUp(level);
                        actions.speedTheBossUp(level);
                    }
                }
            }

            // level 3
            else if (choice == 4) {
                howManyOpponents++;
                if (howManyOpponents % 15 == 0 && opponentsTime) {
                    actions.makeOpponent();
                    actions.makeOpponentShots(4);
                }
                if (howManyOpponents >= 2400) {
                    opponentsTime = false;
                    actions.BossAlive[level - 1] = true;
                }

                if (actions.BossAlive[level - 1]) {
                    actions.moveBoss(level);
                    actions.makeBossShots(level, 30);
                    actions.moveBossShots(level);
                    if (!actions.checkIfBossIsAlive()) {
                        actions.BossAlive[level - 1] = false;
                    }
                }

                // win!
                if (!actions.checkIfBossIsAlive() && !actions.BossAlive[level - 1]) {
                    nextLevel[level - 1]++;
                    actions.clearObjects();
                    if (nextLevel[level - 1] == 300) {
                        isOver = true;
                        choice = 6;
                    }
                }
            }

            actions.movePlayerShots();
            actions.moveOpponents();
            actions.moveOpponentShots();
            actions.playerShotTouchedOpponent(level);
            actions.opponentShotTouchedPlayer();
            actions.didShotsIntersect();
            actions.playerTouchedOpponent(level);
            actions.checkIfBossIsAlive();

            // game over
            if (!actions.checkIfPlayerIsAlive()) {
                keylock = true;
                gameOver++;
                if (gameOver == 300) {
                    choice = 5;
                    isOver = true;
                    healthPanel.health = 100;
                    actions.clearObjects();
                    howManyOpponents = 0;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!keylock) {

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (player.getY() <= 0) {
                    player.setY(0);
                    ;
                } else {
                    player.setY(player.getY() - 15);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (player.getY() >= 470) {
                    player.setY(470);
                    ;
                } else {
                    player.setY(player.getY() + 15);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (player.getX() <= 0) {
                    player.setX(0);
                    ;
                } else {
                    player.setX(player.getX() - 10);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (player.getX() >= 755) {
                    player.setX(755);
                } else {
                    player.setX(player.getX() + 10);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (givenShots < 8) {
                    actions.addPlayerShot(new Shots(actions.player.getX() + 60, actions.player.getY() + 25));
                    givenShots++;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (choice != 1 && choice != 3 && choice != 4) {
                    choice = 1;
                    isOver = false;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_M) {
                choice = 0;
                isOver = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_I) {
                if (choice != 1 && choice != 3 && choice != 4) {
                    choice = 2;
                    isOver = true;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

}
