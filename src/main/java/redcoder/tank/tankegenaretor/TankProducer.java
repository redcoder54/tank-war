package redcoder.tank.tankegenaretor;

import redcoder.tank.TankGameContext;

/**
 * 坦克生产者，不断地生产敌方坦克。
 */
public interface TankProducer extends Runnable {

    /**
     * 生产敌方坦克
     */
    void produce(TankGameContext tgc);

    @Override
    default void run() {
        produce(TankGameContext.getTankGameContext());
    }
}
