package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

/**
 * 游戏关卡切换控制器
 */
public interface GameStageSwitchController {

    void start(TankGameContext tgc);

    StageDeployer getStageDeployer();
}
