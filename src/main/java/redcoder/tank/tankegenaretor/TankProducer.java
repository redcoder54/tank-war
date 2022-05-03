package redcoder.tank.tankegenaretor;

/**
 * 坦克生产者，不断地生产敌方坦克。
 */
public interface TankProducer extends Runnable {

    /**
     * 生产敌方坦克
     */
    void produce();

    @Override
    default void run() {
        produce();
    }
}
