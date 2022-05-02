package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Direction;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 往四个方向发射导弹
 */
public class FourDirectionMissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - missileL.getWidth() / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - missileL.getHeight() / 2;

        TankFrame tankFrame = tank.getTankFrame();
        for (Direction direction : Direction.values()) {
            tankFrame.getBullets().add(new Bullet(bX, bY, direction, tank.getGroup(), tankFrame,
                    missileL, missileU, missileR, missileD));
        }

        fireSound(tank);
    }
}
