package redcoder.tank.tankproducer;

import redcoder.tank.TankGameContext;

/**
 * 坦克生产者，不断地生产敌方坦克。
 */
public interface TankProducer {

    /**
     * 生产敌方坦克
     */
    void produce(TankGameContext tgc);
}
