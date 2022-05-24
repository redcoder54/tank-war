package redcoder.tank.collider;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.GameObj;

import java.io.Serializable;

/**
 * 碰撞器，处理两个相撞的游戏物体。多个碰撞器可组合成一个碰撞器链。
 *
 * @see ColliderChain
 */
public interface Collider extends Serializable {

    /**
     * 碰撞器名称
     */
    String getName();

    /**
     * 处理两个相撞的游戏物体
     *
     * @param o1 游戏物体
     * @param o2 游戏物体
     * @return true：继续执行，false：终止执行。
     */
    boolean collide(GameModel gameModel, GameObj o1, GameObj o2);
}
