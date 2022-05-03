package redcoder.tank.tankegenaretor;

import redcoder.tank.TankGame;
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


    @Override
    public void produce() {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        int tankCount = gameConfig.getInitialTankCount();
        int gameWindowsWidth = gameConfig.getGameWindowsWidth();
        int gameWindowHeight = gameConfig.getGameWindowsHeight();

        while (tankCount > 0) {
            tankCount -= 3;
            try {
                Tank tank1 = new Tank(2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);
                Tank tank2 = new Tank(gameWindowsWidth / 2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);
                Tank tank3 = new Tank(gameWindowHeight - 2, 30, gameConfig.getEnemyTankSpeed(), DOWN, BAD, true);

                TankGame.getInstance().addGameObj(tank1);
                TankGame.getInstance().addGameObj(tank2);
                TankGame.getInstance().addGameObj(tank3);

                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
