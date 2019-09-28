package com.mfh;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author mfh
 * @date 2019/9/28 11:05
 */
public class TankClient extends Frame {
    private int x = 50;
    private int y = 50;
    private Image offScreenImage = null;
    public static void main(String[] args) {
        final TankClient client = new TankClient();
        client.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 30, 30);
        g.setColor(color);
        y++;
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(800, 600);
        }
        /* 图片上的画笔 */
        final Graphics imageGraphics = offScreenImage.getGraphics();
        final Color color = imageGraphics.getColor();
        imageGraphics.setColor(Color.BLUE);
        imageGraphics.fillRect(0,0,800,600);
        imageGraphics.setColor(color);
        paint(imageGraphics);

        g.drawImage(offScreenImage, 0, 0, null);

    }

    private void launchFrame() {
        this.setLocation(200, 300);
        this.setSize(800, 600);
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
