package redcoder.tank.gameobj;

import redcoder.tank.Audio;
import redcoder.tank.ImageResource;
import redcoder.tank.TGC;

import java.awt.*;

public class Boom extends GameObj {

    public static int WIDTH = ImageResource.booms[0].getWidth();
    public static int HEIGHT = ImageResource.booms[0].getHeight();

    private int step = 0;

    public Boom(int x, int y) {
        super(x, y);
        new Thread(() -> new Audio("audio/boom.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageResource.booms[step], x, y, null);
        step = pause ? step : step + 1;

        if (step >= ImageResource.booms.length) {
            TGC.getTGC().removeGameObj(this);
        }
    }
}
