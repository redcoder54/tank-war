package redcoder.tank.stage;

import redcoder.tank.*;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.PlayerTankImageSupplier;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private StageDeployer stageDeployer;

    public DefaultGameStageSwitchController(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
    }

    @Override
    public void start(GameModel gameModel) {
        // 启动
        ScheduledUtils.scheduleAtFixedRate(new StageSwitchTask(gameModel, stageDeployer), 500, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }

    private static class StageSwitchTask implements Runnable {

        private GameModel gameModel;
        private StageDeployer stageDeployer;
        private boolean firstRun = true;

        public StageSwitchTask(GameModel gameModel, StageDeployer stageDeployer) {
            this.gameModel = gameModel;
            this.stageDeployer = stageDeployer;
        }

        @Override
        public void run() {
            try {
                GameProgress gameProgress = gameModel.getGameProgress();
                if (firstRun) {
                    stageDeployer.deploy(gameModel);
                    firstRun = false;
                } else if (gameProgress.isPass()) {
                    gameModel.clearGameObj();
                    resetPlayerTank();
                    gameProgress.nextStage();
                    stageDeployer.deploy(gameModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 重置玩家坦克
        private void resetPlayerTank() {
            Tank oldTank = gameModel.getPlayerTank();
            Tank playerTank = new Tank(TankPanel.WIDTH / 2, TankPanel.HEIGHT - 60, oldTank.getSpeed(), Direction.UP,
                    Group.GOOD, false, PlayerTankImageSupplier.SUPPLIER);
            gameModel.addGameObj(playerTank);
        }
    }

}
