package redcoder.tank;

import java.awt.*;

public class Boom {

    private int x;
    private int y;
    private TankFrame tankFrame;
    private int step = 0;

    public Boom(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Audio("audio/boom.wav").play();
    }

    public void paint(Graphics g) {
        if (step >= ResourceManager.booms.length) {
            tankFrame.getBooms().remove(this);
            return;
        }

        g.drawImage(ResourceManager.booms[step++], x, y, null);
    }
}
