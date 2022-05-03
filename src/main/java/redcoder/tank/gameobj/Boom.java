package redcoder.tank.gameobj;

import redcoder.tank.GameObjType;
import redcoder.tank.Audio;
import redcoder.tank.ResourceManager;
import redcoder.tank.TankGameContext;

import java.awt.*;

public class Boom extends GameObj {

    public static int WIDTH = ResourceManager.booms[0].getWidth();
    public static int HEIGHT = ResourceManager.booms[0].getHeight();

    private int step = 0;

    public Boom(int x, int y) {
        super(x, y, GameObjType.BOOM);
        new Thread(() -> new Audio("audio/boom.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.booms[step++], x, y, null);

        if (step >= ResourceManager.booms.length) {
            TankGameContext.getTankGameContext().removeGameObj(this);
        }
    }
}
