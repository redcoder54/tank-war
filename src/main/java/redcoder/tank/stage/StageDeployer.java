package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

import java.util.List;

/**
 * 关卡部署器，调用关卡生成器创建新的关卡。
 */
public interface StageDeployer {

    void deploy(TankGameContext tgc);

    void addStageGenerator(StageGenerator generator);

    List<StageGenerator> getStageGenerators();
}
