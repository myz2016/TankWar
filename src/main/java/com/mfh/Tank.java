package com.mfh;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author mfh
 * @date 2019/9/29 20:02
 */
public class Tank {
    private int x;
    private int y;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private static final int X_SPEED = 5;
    private static final int Y_SPEED = 5;

    boolean bL = false, bU = false, bR = false, bD = false;

    /**
     * 方向枚举
     */
    enum DirectionEnum {L, LU, U, RU, R, RD, D, LD, STOP}

    private DirectionEnum dir = DirectionEnum.STOP;

    public Tank() {
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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
            case STOP:
                break;
            default:
        }
    }

    private void locateDirection() {
        if (bL && !bU && !bR && !bD) { dir = DirectionEnum.L; }
        if (bL && bU && !bR && !bD) { dir = DirectionEnum.LU; }
        if (!bL && bU && !bR && !bD) { dir = DirectionEnum.U; }
        if (!bL && bU && bR && !bD) { dir = DirectionEnum.RU; }
        if (!bL && !bU && bR && !bD) { dir = DirectionEnum.R; }
        if (!bL && !bU && bR && bD) { dir = DirectionEnum.RD; }
        if (!bL && !bU && !bR && bD) { dir = DirectionEnum.D; }
        if (bL && !bU && !bR && bD) { dir = DirectionEnum.LD; }
    }

    void draw(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(this.x, this.y, Tank.WIDTH, Tank.HEIGHT);
        g.setColor(color);
    }

    void keyPressed(KeyEvent e) {
        final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            default:
        }
        locateDirection();
        move();
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
