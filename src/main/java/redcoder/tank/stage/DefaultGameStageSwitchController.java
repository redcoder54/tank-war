package redcoder.tank.stage;

import redcoder.tank.GameProgress;
import redcoder.tank.TGC;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private StageDeployer stageDeployer;

    public DefaultGameStageSwitchController(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
    }

    @Override
    public GameProgress start(TGC tgc) {
        // 启动
        ScheduledUtils.scheduleAtFixedRate(new StageSwitchTask(tgc, stageDeployer), 500, 2000, TimeUnit.MILLISECONDS);
        return new GameProgress();
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }

    private static class StageSwitchTask implements Runnable {

        private TGC tgc;
        private StageDeployer stageDeployer;
        private boolean firstRun = true;

        public StageSwitchTask(TGC tgc, StageDeployer stageDeployer) {
            this.tgc = tgc;
            this.stageDeployer = stageDeployer;
        }

        @Override
        public void run() {
            try {
                GameProgress gameProgress = tgc.getGameProgress();
                if (firstRun) {
                    stageDeployer.deploy(tgc);
                    firstRun = false;
                } else if (gameProgress.isPass()) {
                    tgc.clearGameObj();
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
