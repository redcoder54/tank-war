package redcoder.tank.stage;

import redcoder.tank.TankPanel;
import redcoder.tank.gameobj.Direction;
import redcoder.tank.gameobj.Group;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.PlayerTankImageSupplier;
import redcoder.tank.model.GameModel;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultGameStageSwitchController implements GameStageSwitchController {

    private static final Logger LOGGER = Logger.getLogger(DefaultGameStageSwitchController.class.getName());
    private StageDeployer stageDeployer;

    public DefaultGameStageSwitchController(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
    }

    @Override
    public void nextStage(GameModel gameModel) {
        gameModel.clearGameObj();
        resetPlayerTank(gameModel);

        try {
            stageDeployer.deploy(gameModel);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "DefaultGameStageSwitchController.nextStage", e);
        }
    }

    @Override
    public StageDeployer getStageDeployer() {
        return this.stageDeployer;
    }

    // 重置玩家坦克
    private void resetPlayerTank(GameModel gameModel) {
        Tank oldTank = gameModel.getPlayerTank();
        Tank playerTank = new Tank(TankPanel.WIDTH / 2, TankPanel.HEIGHT - 60, oldTank.getSpeed(), Direction.UP,
                Group.GOOD, false, PlayerTankImageSupplier.SUPPLIER);
        gameModel.setPlayerTank(playerTank);
        gameModel.addGameObj(playerTank);
    }
}
