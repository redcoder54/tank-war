package redcoder.tank.gameobj;

import redcoder.tank.model.GameModel;
import redcoder.tank.model.GameModelWrapper;
import redcoder.tank.pauseresume.PauseResumeEvent;
import redcoder.tank.pauseresume.PauseResumeEventType;
import redcoder.tank.pauseresume.PauseResumeListener;

import java.awt.*;
import java.io.Serializable;

/**
 * 游戏物体
 */
public abstract class GameObj implements PauseResumeListener, Serializable {

    protected int x;
    protected int y;
    protected boolean pause = false;
    protected int prIndex;

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

    public void setPrIndex(int prIndex) {
        this.prIndex = prIndex;
    }

    @Override
    public void onPauseResume(PauseResumeEvent event) {
        PauseResumeEventType eventType = event.getEventType();
        pause = eventType == PauseResumeEventType.PAUSE;
    }
}
