package redcoder.tank;

import java.awt.*;

public class Boom {

    private int x;
    private int y;

    private int step = 0;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        if (step >= ResourceManager.booms.length) return;
        if(step==0) new Audio("audio/explode.wav").play();

        g.drawImage(ResourceManager.booms[step++], x, y, null);
    }
}
