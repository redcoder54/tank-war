package redcoder.tank.tankproducer;

import redcoder.tank.TGC;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.Tank;

import java.util.concurrent.TimeUnit;

import static redcoder.tank.Direction.DOWN;
import static redcoder.tank.Group.BAD;

/**
 * 坦克生产者，每隔3秒生成n个敌方坦克（n <= 3）。当屏幕上的敌方坦克数量大于等于{@link #MAXIMUM_ON_SCREEN}时，暂停生产坦克，
 * 直到坦克数量小于{@link #MAXIMUM_ON_SCREEN}时，继续生产坦克。
 */
public class DefaultTankProducer implements TankProducer {

    /**
     * 屏幕上可出现的敌方坦克最大数量。
     */
    private static final int MAXIMUM_ON_SCREEN = 6;

    @Override
    public void produce(TGC tgc) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        int tankCount = gameConfig.getInitialTankCount();
        int gameWindowsWidth = gameConfig.getGameWindowsWidth();
        int gameWindowHeight = gameConfig.getGameWindowsHeight();

        while (tankCount > 0) {
            int i = MAXIMUM_ON_SCREEN - tgc.getGameProgress().getLivingTankCount();
            if (i >= 3) {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowsWidth / 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowHeight - 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount -= 3;
            } else if (i >= 2) {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tgc.addGameObj(new Tank(gameWindowsWidth / 2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount -= 2;
            } else if (i >= 1) {
                tgc.addGameObj(new Tank(2, 50, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true));
                tankCount--;
            }
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
