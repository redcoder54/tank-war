package redcoder.tank.gameobj;

import redcoder.tank.Audio;
import redcoder.tank.ResourceManager;
import redcoder.tank.TankGame;

import java.awt.*;

public class Boom extends GameObj {

    public static int WIDTH = ResourceManager.booms[0].getWidth();
    public static int HEIGHT = ResourceManager.booms[0].getHeight();

    private int step = 0;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> new Audio("audio/boom.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.booms[step++], x, y, null);

        if (step >= ResourceManager.booms.length) {
            TankGame.getInstance().removeGameObj(this);
        }
    }
}
