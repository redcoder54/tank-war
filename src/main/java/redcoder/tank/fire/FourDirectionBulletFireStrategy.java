package redcoder.tank.fire;

import redcoder.tank.*;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.GeneralBulletImageSupplier;

/**
 * 往四个方向射击子弹
 */
public class FourDirectionBulletFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bY = tank.getY() + tank.getWidth() / 2 - GeneralBulletImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bX = tank.getX() + tank.getHeight() / 2 - GeneralBulletImageSupplier.SUPPLIER.getImageHeight() / 2;
        for (Direction direction : Direction.values()) {
            TGC.getTGC().addGameObj(new Bullet(bX, bY, direction, tank.getGroup(),GeneralBulletImageSupplier.SUPPLIER));
        }

        fireSound(tank);
    }
}
