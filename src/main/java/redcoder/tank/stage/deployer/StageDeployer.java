package redcoder.tank.stage.deployer;

import redcoder.tank.model.GameModel;
import redcoder.tank.stage.generator.StageGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * 关卡部署器，调用关卡生成器创建新的关卡。
 */
public interface StageDeployer extends Serializable {

    void deploy(GameModel gameModel);

    void addStageGenerator(StageGenerator generator);

    List<StageGenerator> getStageGenerators();
}
