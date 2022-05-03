package redcoder.tank.stage.deployer;

import redcoder.tank.TankGameContext;
import redcoder.tank.stage.generator.StageGenerator;

import java.util.List;

/**
 * 关卡部署器，调用关卡生成器创建新的关卡。
 */
public interface StageDeployer {

    void deploy(TankGameContext tgc);

    void addStageGenerator(StageGenerator generator);

    List<StageGenerator> getStageGenerators();
}
