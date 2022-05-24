package redcoder.tank.gameobj;

import redcoder.tank.Resumable;
import redcoder.tank.model.GameModel;

import java.awt.*;
import java.io.Serializable;

/**
 * 游戏物体
 */
public abstract class GameObj implements Resumable, Serializable {

    protected int x;
    protected int y;
    protected boolean pause = false;

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void paint(Graphics g, GameModel gameModel);

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
