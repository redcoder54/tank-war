package redcoder.tank.gameobj;

import redcoder.tank.GameObjType;

import java.awt.*;

/**
 * 游戏物体
 */
public abstract class GameObj {

    protected int x;
    protected int y;
    protected GameObjType type;

    public GameObj(int x, int y, GameObjType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameObjType getType() {
        return type;
    }
}
