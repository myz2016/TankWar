package com.mfh;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author mfh
 * @date 2019/9/28 11:05
 */
public class TankClient extends Frame {
    private int tankX = 50;
    private int tankY = 50;
    /** 屏幕宽度 */
    private static final int SCREEN_WIDTH = 800;
    /** 屏幕高度 */
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_X = 200;
    private static final int SCREEN_Y = 300;
    /** 坦克宽度 */
    private static final int TANK_WIDTH = 30;
    /** 坦克高度 */
    private static final int TANK_HEIGHT = 30;

    private Image offScreenImage = null;
    public static void main(String[] args) {
        final TankClient client = new TankClient();
        client.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(tankX, tankY, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(color);
        tankY++;
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
        imageGraphics.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
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
