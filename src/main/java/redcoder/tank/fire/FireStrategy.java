package redcoder.tank.fire;

import redcoder.tank.GameModel;
import redcoder.tank.gameobj.Tank;

import java.io.Serializable;

/**
 * 策略类，坦克如何开火
 */
public interface FireStrategy extends Serializable {

    void fire(GameModel gameModel, Tank tank);
}
