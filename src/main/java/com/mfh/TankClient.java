package com.mfh;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author mfh
 * @date 2019/9/28 11:05
 */
public class TankClient extends Frame {
    private void launchFrame() {
        this.setLocation(200, 300);
        this.setSize(800, 600);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
    public static void main(String[] args) {
        final TankClient client = new TankClient();
        client.launchFrame();
    }
}
