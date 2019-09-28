package com.mfh;

import java.awt.*;

/**
 * @author mfh
 * @date 2019/9/28 11:05
 */
public class TankClient extends Frame {
    private void launchFrame() {
        this.setLocation(200, 300);
        this.setSize(800, 600);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        final TankClient client = new TankClient();
        client.launchFrame();
    }
}
