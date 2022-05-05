package redcoder.tank.gameobj;

import redcoder.tank.Resumable;

import java.awt.*;

/**
 * 游戏物体
 */
public abstract class GameObj implements Resumable {

    protected int x;
    protected int y;
    protected boolean pause = false;

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void resume() {
        pause = false;
    }
}
