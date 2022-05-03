package redcoder.tank.stage;

import redcoder.tank.GameProgress;
import redcoder.tank.TankGameContext;
import redcoder.tank.stage.deployer.StageDeployer;

/**
 * 游戏关卡切换控制器
 */
public interface GameStageSwitchController {

    GameProgress start(TankGameContext tgc);

    StageDeployer getStageDeployer();
}
