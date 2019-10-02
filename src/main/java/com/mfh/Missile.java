package com.mfh;

import java.awt.*;

/**
 * @author mfh
 * @date 2019/10/1 18:06
 */
public class Missile {
    private int x;
    private int y;
    private Tank.DirectionEnum dir;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int X_SPEED = 7;
    private static final int Y_SPEED = 7;

    public Missile() {}

    public Missile(int x, int y, Tank.DirectionEnum dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    void draw(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.black);
        g.fillOval(this.x - WIDTH/2, this.y - HEIGHT/2, WIDTH, HEIGHT);
        g.setColor(color);

        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= X_SPEED;
                break;
            case LU:
                x -= X_SPEED;
                y -= Y_SPEED;
                break;
            case U:
                y -= Y_SPEED;
                break;
            case RU:
                y -= Y_SPEED;
                x += X_SPEED;
                break;
            case R:
                x += X_SPEED;
                break;
            case RD:
                x += X_SPEED;
                y += Y_SPEED;
                break;
            case D:
                y += Y_SPEED;
                break;
            case LD:
                y += Y_SPEED;
                x -= X_SPEED;
                break;
            default:
        }
    }
}
