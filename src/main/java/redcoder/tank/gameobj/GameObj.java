package redcoder.tank.gameobj;

import java.awt.*;

/**
 * 游戏物体
 */
public abstract class GameObj {

    protected int x;
    protected int y;
    protected boolean pause;

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

    public void pause() {
        pause = true;
    }

    public void resume(){
        pause = false;
    }
}
