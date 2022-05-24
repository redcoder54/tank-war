package redcoder.tank.producer;

import redcoder.tank.model.GameModel;
import redcoder.tank.Resumable;

import java.io.Serializable;

/**
 * 坦克生产者，不断地生产敌方坦克。
 */
public interface ResettableTankProducer extends Resumable, Serializable {

    /**
     * 重置生产者
     */
    void reset();

    /**
     * 生产敌方坦克
     */
    void produce(GameModel gameModel);
}
