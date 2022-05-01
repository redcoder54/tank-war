package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Direction;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 往四个方向发射导弹
 */
public class FourDirectionMissileFireStrategy extends VocalFireStrategySupport {

    @Override
    protected void doFire(Tank tank) {
        TankFrame tankFrame = tank.getTankFrame();
        int bx = tank.getX() + Tank.WIDTH / 2 - missileL.getWidth() / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - missileL.getHeight() / 2;

        for (Direction direction : Direction.values()) {
            tankFrame.getBullets().add(new Bullet(bx, by, direction, tank.getGroup(), tankFrame,
                    missileL, missileU, missileR, missileD));
        }
    }
}
