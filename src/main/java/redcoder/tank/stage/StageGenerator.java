package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

/**
 * 关卡生成器，负责每一个游戏关卡内容的生成，包括障碍物的生成、敌方坦克的生成。
 */
public interface StageGenerator {

    /**
     * 碰撞器名称
     */
    String getName();

    /**
     * 生成新的关卡
     *
     * @param tgc
     */
    void generate(TankGameContext tgc);
}
