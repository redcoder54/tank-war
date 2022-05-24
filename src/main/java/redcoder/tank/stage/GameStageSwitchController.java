package redcoder.tank.stage;

import redcoder.tank.model.GameModel;
import redcoder.tank.stage.deployer.StageDeployer;

import java.io.Serializable;

/**
 * 游戏关卡切换控制器
 */
public interface GameStageSwitchController extends Serializable {

    void start(GameModel gameModel);

    void stop(GameModel gameModel);

    boolean isStart();

    StageSwitchTask getStageSwitchTask();

    StageDeployer getStageDeployer();
}
