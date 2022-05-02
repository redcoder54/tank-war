package redcoder.tank.gameobj;

import java.awt.*;

/**
 * 游戏物体
 */
public abstract class GameObj {

    protected int x;
    protected int y;

    public abstract void paint(Graphics g);
}
