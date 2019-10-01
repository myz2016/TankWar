package com.mfh;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mfh
 * @date 2019/9/28 11:05
 */
public class TankClient extends Frame {

    private Tank tank = new Tank(50, 50);

    /** 敌人坦克 */
    private List<Tank> enemyTanks = new ArrayList<Tank>() {{
        int x = 20;
        int y = 500;
        int max = 10;
        for (int i = 0; i < max; i++) {
            add(new Tank(x += Tank.WIDTH, y));
        }
    }};
    /**
     * 屏幕宽度
     */
    private static final int SCREEN_WIDTH = 800;
    /**
     * 屏幕高度
     */
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_X = 200;
    private static final int SCREEN_Y = 300;

    private Image offScreenImage = null;

    public static void main(String[] args) {
        final TankClient client = new TankClient();
        client.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        tank.draw(g);
        paintEnemyTanks(g);
    }

    private void paintEnemyTanks(Graphics g) {
        for (Tank enemyTank : enemyTanks) {
            enemyTank.draw(g);
        }
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        }
        /* 图片上的画笔 */
        final Graphics imageGraphics = offScreenImage.getGraphics();
        final Color color = imageGraphics.getColor();
        imageGraphics.setColor(Color.BLUE);
        imageGraphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        imageGraphics.setColor(color);
        paint(imageGraphics);

        g.drawImage(offScreenImage, 0, 0, null);

    }

    private void launchFrame() {
        this.setLocation(SCREEN_X, SCREEN_Y);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                tank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tank.keyReleased(e);
            }
        });
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        final Thread pt = new Thread(new PaintThread());
        pt.start();
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
