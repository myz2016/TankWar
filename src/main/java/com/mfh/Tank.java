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

    private TankClient client;


    /**
     * 方向枚举
     */
    enum DirectionEnum {L, LU, U, RU, R, RD, D, LD, STOP,;}
    private DirectionEnum dir = DirectionEnum.STOP;

    public Tank() {
    }

    Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, TankClient client) {
        this.x = x;
        this.y = y;
        this.client = client;
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

    /**
     * 定位方向
     */
    private void locateDirection() {
        if (bL && !bU && !bR && !bD) { dir = DirectionEnum.L; }
        if (bL && bU && !bR && !bD) { dir = DirectionEnum.LU; }
        if (!bL && bU && !bR && !bD) { dir = DirectionEnum.U; }
        if (!bL && bU && bR && !bD) { dir = DirectionEnum.RU; }
        if (!bL && !bU && bR && !bD) { dir = DirectionEnum.R; }
        if (!bL && !bU && bR && bD) { dir = DirectionEnum.RD; }
        if (!bL && !bU && !bR && bD) { dir = DirectionEnum.D; }
        if (bL && !bU && !bR && bD) { dir = DirectionEnum.LD; }
        if (!bL && !bU && !bR && !bD) { dir = DirectionEnum.STOP; }
    }

    void draw(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(this.x, this.y, Tank.WIDTH, Tank.HEIGHT);
        g.setColor(color);
        move();
    }

    void keyReleased(KeyEvent e) {
        final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            default:
        }
        locateDirection();
    }

    void keyPressed(KeyEvent e) {
        final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_CONTROL:
                final Missile missile = this.fire();
                this.client.setMissile(missile);
                break;
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
    }

    private Missile fire() {
        return new Missile(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, dir);
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
