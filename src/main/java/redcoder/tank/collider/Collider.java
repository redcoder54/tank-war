package redcoder.tank.collider;

import redcoder.tank.GameModel;
import redcoder.tank.gameobj.GameObj;

/**
 * 碰撞器，处理两个相撞的游戏物体。多个碰撞器可组合成一个碰撞器链。
 *
 * @see ColliderChain
 */
public interface Collider {

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
