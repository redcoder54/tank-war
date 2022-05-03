package redcoder.tank.tankproducer;

import redcoder.tank.TankGameContext;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.Tank;

import java.util.concurrent.TimeUnit;

import static redcoder.tank.Direction.DOWN;
import static redcoder.tank.Group.BAD;

/**
 * 默认的坦克生产者，每隔3秒生成3个敌方坦克
 */
public class DefaultTankProducer implements TankProducer {

    private static final int MAXIMUM_ON_SCREEN = 6;

    @Override
    public void produce(TankGameContext tgc) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        int tankCount = gameConfig.getInitialTankCount();
        int gameWindowsWidth = gameConfig.getGameWindowsWidth();
        int gameWindowHeight = gameConfig.getGameWindowsHeight();

        while (tankCount > 0) {
            if (tankCount == 1) {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount--;
            } else if (tankCount == 2) {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowsWidth / 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount -= 2;
            } else {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowsWidth / 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowHeight - 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount -= 3;
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
