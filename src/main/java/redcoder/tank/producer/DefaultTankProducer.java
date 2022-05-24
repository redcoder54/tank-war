package redcoder.tank.producer;

import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.model.GameModel;
import redcoder.tank.TankPanel;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.EnemyTankImageSupplier;
import redcoder.tank.model.GameProgress;

import java.util.concurrent.TimeUnit;

import static redcoder.tank.gameobj.Direction.DOWN;
import static redcoder.tank.gameobj.Group.BAD;

/**
 * 坦克生产者，每隔3秒生成n个敌方坦克（n <= 3）。当屏幕上的敌方坦克数量大于等于{@link #MAXIMUM_ON_SCREEN}时，暂停生产坦克，
 * 直到坦克数量小于{@link #MAXIMUM_ON_SCREEN}时，继续生产坦克。
 */
public class DefaultTankProducer implements ResettableTankProducer {

    /**
     * 屏幕上可出现的敌方坦克最大数量。
     */
    private static final int MAXIMUM_ON_SCREEN = 6;

    private boolean paused = false;
    private int tankCount;
    private int tankSpeed;
    
    public DefaultTankProducer(){
        reset();
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void reset() {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        tankCount = gameConfig.getInitialTankCount();
        tankSpeed = gameConfig.getEnemyTankSpeed();
    }

    @Override
    public void produce(GameModel gameModel) {
        while (tankCount > 0) {
            if (paused) {
                takeRest();
                continue;
            }
            GameProgress gameProgress = gameModel.getGameProgress();
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
            System.out.println(gameProgress);
            takeRest();
        }
    }

    private void takeRest() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
