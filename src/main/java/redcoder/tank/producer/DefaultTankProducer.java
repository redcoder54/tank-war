package redcoder.tank.producer;

import redcoder.tank.TankPanel;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.EnemyTankImageSupplier;
import redcoder.tank.model.GameModel;
import redcoder.tank.model.GameModelWrapper;
import redcoder.tank.model.GameProgress;
import redcoder.tank.pauseresume.PauseResumeEvent;
import redcoder.tank.pauseresume.PauseResumeEventType;
import redcoder.tank.pauseresume.PauseResumeListener;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static redcoder.tank.gameobj.Direction.DOWN;
import static redcoder.tank.gameobj.Group.BAD;

/**
 * 坦克生产者，每隔3秒生成n个敌方坦克（n <= 3）。当屏幕上的敌方坦克数量大于等于{@link #MAXIMUM_ON_SCREEN}时，暂停生产坦克，
 * 直到坦克数量小于{@link #MAXIMUM_ON_SCREEN}时，继续生产坦克。
 */
public class DefaultTankProducer implements TankProducer, PauseResumeListener {

    private static final Logger LOGGER = Logger.getLogger(DefaultTankProducer.class.getName());

    // 屏幕上可出现的敌方坦克最大数量。
    private static final int MAXIMUM_ON_SCREEN = 6;

    private boolean stop = false;
    private int tankCount;
    private int tankSpeed;
    private boolean paused = false;
    private int prIndex;

    public DefaultTankProducer() {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        tankCount = gameConfig.getInitialTankCount();
        tankSpeed = gameConfig.getEnemyTankSpeed();

        GameModel gameModel = GameModelWrapper.getGameModel();
        prIndex = gameModel.getListeners().addPauseResumeListener(this);
        gameModel.setTankProducer(this);
    }

    @Override
    public void produce() {
        try {
            GameModel gameModel = GameModelWrapper.getGameModel();
            GameProgress gameProgress = gameModel.getGameProgress();
            while (tankCount > 0 && !stop) {
                if (paused) {
                    takeRest();
                    continue;
                }
                int addableTankCount = Math.min(tankCount, MAXIMUM_ON_SCREEN - gameProgress.getLivingTankCount());
                if (addableTankCount >= 3) {
                    gameModel.addGameObj(new Tank(2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    gameModel.addGameObj(new Tank(TankPanel.WIDTH / 2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    gameModel.addGameObj(new Tank(TankPanel.WIDTH - 2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    tankCount -= 3;
                } else if (addableTankCount >= 2) {
                    gameModel.addGameObj(new Tank(2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    gameModel.addGameObj(new Tank(TankPanel.WIDTH / 2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    tankCount -= 2;
                } else if (addableTankCount >= 1) {
                    gameModel.addGameObj(new Tank(2, 0, tankSpeed, DOWN, BAD, true, EnemyTankImageSupplier.SUPPLIER));
                    tankCount--;
                }
                LOGGER.log(Level.INFO, "{0}, {1}", new String[]{toString(), gameProgress.toString()});
                takeRest();
            }

            gameModel.getListeners().removePauseResumeListener(prIndex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "DefaultTankProducer.produce", e);
        }
    }

    @Override
    public void stop() {
        stop = true;
        GameModelWrapper.getGameModel().getListeners().removePauseResumeListener(prIndex);
    }

    private void takeRest() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Override
    public void onPauseResume(PauseResumeEvent event) {
        PauseResumeEventType eventType = event.getEventType();
        paused = eventType == PauseResumeEventType.PAUSE;
    }
}
