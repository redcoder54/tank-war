package redcoder.tank.stage;

import redcoder.tank.GameProgress;
import redcoder.tank.TankGameContext;
import redcoder.tank.stage.deployer.StageDeployer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private StageDeployer stageDeployer;
    private ScheduledExecutorService scheduledExecutorService;

    public DefaultGameStageSwitchController(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public GameProgress start(TankGameContext tgc) {
        // 启动
        scheduledExecutorService.scheduleAtFixedRate(new StageSwitchTask(tgc, stageDeployer), 500, 2000, TimeUnit.MILLISECONDS);
        return new GameProgress();
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }

    private static class StageSwitchTask implements Runnable {

        private TankGameContext tgc;
        private StageDeployer stageDeployer;
        private boolean firstRun = false;

        public StageSwitchTask(TankGameContext tgc, StageDeployer stageDeployer) {
            this.tgc = tgc;
            this.stageDeployer = stageDeployer;
        }

        @Override
        public void run() {
            try {
                GameProgress gameProgress = tgc.getGameProgress();
                if (!firstRun) {
                    stageDeployer.deploy(tgc);
                    firstRun = true;
                } else if (gameProgress.isPass()) {
                    tgc.resetGameObj();
                    tgc.resetPlayerTank();
                    gameProgress.nextStage();
                    stageDeployer.deploy(tgc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
