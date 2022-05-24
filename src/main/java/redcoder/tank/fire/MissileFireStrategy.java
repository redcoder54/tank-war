package redcoder.tank.fire;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.MissileImageSupplier;

/**
 * 发射一颗导弹
 */
public class MissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        // FIXME: 2022/5/23
        int bY = tank.getY() + tank.getWidth() / 2 - MissileImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bX = tank.getX() + tank.getHeight() / 2 - MissileImageSupplier.SUPPLIER.getImageHeight() / 2;
        TGC.getTGC().addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(),MissileImageSupplier.SUPPLIER));

        fireSound(tank);
    }
}
