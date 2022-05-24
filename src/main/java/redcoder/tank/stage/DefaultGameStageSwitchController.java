package redcoder.tank.stage;

import redcoder.tank.model.GameProgress;
import redcoder.tank.TankPanel;
import redcoder.tank.gameobj.Direction;
import redcoder.tank.gameobj.Group;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.PlayerTankImageSupplier;
import redcoder.tank.model.GameModel;
import redcoder.tank.model.GameModelWrapper;
import redcoder.tank.stage.deployer.StageDeployer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private static final Logger LOGGER = Logger.getLogger(DefaultGameStageSwitchController.class.getName());

    private StageDeployer stageDeployer;
    private StageSwitchTask task;
    private transient boolean start = false;

    public DefaultGameStageSwitchController(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
        this.task = new DefaultStageSwitchTask(stageDeployer);
    }

    @Override
    public void start(GameModel gameModel) {
        if (start) {
            LOGGER.log(Level.WARNING, "DefaultGameStageSwitchController has already started.");
            return;
        }
        start = true;
        task.start();
    }

    @Override
    public void stop(GameModel gameModel) {
        task.stop();
    }

    @Override
    public boolean isStart() {
        return start;
    }

    @Override
    public StageSwitchTask getStageSwitchTask() {
        return task;
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }

    private static class DefaultStageSwitchTask implements StageSwitchTask {

        private transient boolean run = false;
        private boolean firstRun = true;
        private StageDeployer stageDeployer;

        public DefaultStageSwitchTask(StageDeployer stageDeployer) {
            this.stageDeployer = stageDeployer;
        }

        @Override
        public void start() {
            if (run) {
                return;
            }
            run = true;
            Thread thread = new Thread(() -> {
                try {
                    // 延迟运行，让GameModel先初始化完成
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    // ignore
                }

                while (run) {
                    try {
                        GameModel gameModel = GameModelWrapper.getGameModel();
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

                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "DefaultStageSwitchTask", e);
                    }
                }
            }, "DefaultStageSwitchTask");
            thread.start();
        }

        @Override
        public void stop() {
            run = false;
        }

        // 重置玩家坦克
        private void resetPlayerTank() {
            GameModel gameModel = GameModelWrapper.getGameModel();
            Tank oldTank = gameModel.getPlayerTank();
            Tank playerTank = new Tank(TankPanel.WIDTH / 2, TankPanel.HEIGHT - 60, oldTank.getSpeed(), Direction.UP,
                    Group.GOOD, false, PlayerTankImageSupplier.SUPPLIER);
            gameModel.setPlayerTank(playerTank);
            gameModel.addGameObj(playerTank);
        }
    }

}
