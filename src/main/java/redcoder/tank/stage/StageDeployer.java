package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

import java.util.List;

/**
 * 关卡布置器，负责控制关卡的生成过程
 */
public interface StageDeployer extends Runnable {

    void deploy(TankGameContext tgc);

    void addStageGenerator(StageGenerator generator);

    List<StageGenerator> getStageGenerators();

    @Override
    default void run() {
        deploy(TankGameContext.getTankGameContext());
    }
}
