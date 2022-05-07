package redcoder.tank.fire;

import redcoder.tank.Direction;
import redcoder.tank.TGC;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;

import static redcoder.tank.ImageResource.*;

/**
 * 往四个方向发射导弹
 */
public class FourDirectionMissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - missileL.getWidth() / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - missileL.getHeight() / 2;

        for (Direction direction : Direction.values()) {
            TGC.getTGC().addGameObj(new Bullet(bX, bY, direction, tank.getGroup(),
                    missileL, missileU, missileR, missileD));
        }

        fireSound(tank);
    }
}
