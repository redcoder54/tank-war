package redcoder.tank.stage;

import redcoder.tank.GameProgress;
import redcoder.tank.TankGameContext;

import java.util.concurrent.TimeUnit;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private StageDeployer stageDeployer;

    @Override
    public void start(TankGameContext tgc) {
        this.stageDeployer = tgc.getStageDeployer();

        // 启动
        new Thread(() -> {
            this.stageDeployer.deploy(tgc);

            while (true) {
                try {
                    GameProgress gameProgress = tgc.getGameProgress();
                    if (gameProgress.isPass()) {
                        tgc.resetGameObj();
                        tgc.resetPlayerTank();
                        gameProgress.incrementStage();
                        this.stageDeployer.deploy(tgc);
                    }

                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DefaultGameStageSwitchController").start();
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }
}
