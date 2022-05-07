package redcoder.tank.tankproducer;

import redcoder.tank.GameConfig;
import redcoder.tank.GameConfigFactory;
import redcoder.tank.TGC;
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

    private boolean paused = false;

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void produce(TGC tgc) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();
        int tankCount = gameConfig.getInitialTankCount();
        int enemyTankSpeed = gameConfig.getEnemyTankSpeed();

        while (tankCount > 0) {
            if (paused) {
                takeRest();
                continue;
            }
            // int addableTankCount = Math.min(tankCount, MAXIMUM_ON_SCREEN - tgc.getGameProgress().getLivingTankCount());
            if (tankCount >= 3) {
                tgc.addGameObj(new Tank(2, 50, enemyTankSpeed, DOWN, BAD, true));
                tgc.addGameObj(new Tank(TGC.WIDTH / 2, 50, enemyTankSpeed, DOWN, BAD, true));
                tgc.addGameObj(new Tank(TGC.HEIGHT - 2, 50, enemyTankSpeed, DOWN, BAD, true));
                tankCount -= 3;
            } else if (tankCount >= 2) {
                tgc.addGameObj(new Tank(2, 50, enemyTankSpeed, DOWN, BAD, true));
                tgc.addGameObj(new Tank(TGC.WIDTH / 2, 50, enemyTankSpeed, DOWN, BAD, true));
                tankCount -= 2;
                // } else if (tankCount >= 1) {
            } else {
                tgc.addGameObj(new Tank(2, 50, enemyTankSpeed, DOWN, BAD, true));
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
