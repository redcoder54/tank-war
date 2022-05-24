package redcoder.tank.producer;

import java.io.Serializable;

/**
 * 坦克生产者，不断地生产敌方坦克。
 * 注意：TankProducer继承了Runnable，坦克的生产是一个持续的过程，因此应该放在线程中执行，避免阻塞主线程。
 */
public interface TankProducer extends Runnable, Serializable {

    @Override
    default void run() {
        produce();
    }

    /**
     * 生产敌方坦克
     */
    void produce();

    /**
     * 停止生产
     */
    void stop();
}
