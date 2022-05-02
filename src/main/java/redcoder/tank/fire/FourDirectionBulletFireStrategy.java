package redcoder.tank.fire;

import redcoder.tank.*;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;

import static redcoder.tank.ResourceManager.*;

/**
 * 往四个方向射击子弹
 */
public class FourDirectionBulletFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bY = tank.getY() + Tank.HEIGHT / 2 - bulletL.getHeight() / 2;
        int bX = tank.getX() + Tank.WIDTH / 2 - bulletL.getWidth() / 2;

        TankGame tankGame = tank.getTankGame();
        for (Direction direction : Direction.values()) {
            tankGame.getGameObjs().add(new Bullet(bX, bY, direction, tank.getGroup(), tankGame,
                    bulletL, bulletU, bulletR, bulletD));
        }

        fireSound(tank);
    }
}
