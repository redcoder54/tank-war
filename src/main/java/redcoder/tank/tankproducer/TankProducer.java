package redcoder.tank.tankproducer;

import redcoder.tank.Resumable;
import redcoder.tank.TGC;

/**
 * 坦克生产者，不断地生产敌方坦克。
 */
public interface TankProducer extends Resumable {

    /**
     * 生产敌方坦克
     */
    void produce(TGC tgc);
}
