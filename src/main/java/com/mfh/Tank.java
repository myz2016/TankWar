package com.mfh;

/**
 * @author mfh
 * @date 2019/9/29 20:02
 */
public class Tank {
    private int x;
    private int y;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public Tank() {}

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
