package redcoder.tank.tankegenaretor;

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

    /**
     * 屏幕上最多出现的敌方坦克数量
     */
    private static final int MAXIMUM_ON_SCREEN = 6;

    @Override
    public void produce(TankGameContext tgc) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        int tankCount = gameConfig.getInitialTankCount();
        int gameWindowsWidth = tgc.getWidth();
        int gameWindowHeight = tgc.getHeight();

        while (tankCount > 0) {
            tankCount -= 3;
            try {
                Tank tank1 = new Tank(2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);
                Tank tank2 = new Tank(gameWindowsWidth / 2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);
                Tank tank3 = new Tank(gameWindowHeight - 2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);

                tgc.addGameObj(tank1);
                tgc.addGameObj(tank2);
                tgc.addGameObj(tank3);

                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
