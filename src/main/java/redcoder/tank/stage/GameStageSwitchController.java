package redcoder.tank.stage;

import redcoder.tank.GameModel;
import redcoder.tank.stage.deployer.StageDeployer;

/**
 * 游戏关卡切换控制器
 */
public interface GameStageSwitchController {

    void start(GameModel gameModel);

    StageDeployer getStageDeployer();
}
