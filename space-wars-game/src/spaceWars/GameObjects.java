package spaceWars;

import java.awt.Graphics;


public class GameObjects {


    protected int x;
    
    protected int y;
    protected int xVal;
    protected int yVal;

    public GameObjects(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
    }

    public void render(Graphics g, int ID) {
    }

    public void renderExploded(Graphics g) {
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }

    public void setXVal(int xVal) {
        this.xVal = xVal;
    }

    public void setYVal(int yVal) {
        this.yVal = yVal;
    }

    public int getXVal() {
        return this.xVal;
    }


    public int getYVal() {
        return this.yVal;
    }

    public void movementY() {
        this.y = this.y + this.yVal;
    }

    public void movementX() {
        this.x = this.x + this.xVal;
    }

}
