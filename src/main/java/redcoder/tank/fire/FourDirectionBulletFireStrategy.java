package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Direction;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 往四个方向射击子弹
 */
public class FourDirectionBulletFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bY = tank.getY() + Tank.HEIGHT / 2 - bulletL.getHeight() / 2;
        int bX = tank.getX() + Tank.WIDTH / 2 - bulletL.getWidth() / 2;

        TankFrame tankFrame = tank.getTankFrame();
        for (Direction direction : Direction.values()) {
            tankFrame.getBullets().add(new Bullet(bX, bY, direction, tank.getGroup(), tankFrame,
                    bulletL, bulletU, bulletR, bulletD));
        }

        fireSound(tank);
    }
}
