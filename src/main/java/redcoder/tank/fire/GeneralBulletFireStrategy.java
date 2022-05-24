package redcoder.tank.fire;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.GeneralBulletImageSupplier;

/**
 * 往坦克前进方向发射一颗普通的子弹
 */
public class GeneralBulletFireStrategy extends VocalFireStrategySupport implements FireStrategy {


    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + tank.getWidth() / 2 - GeneralBulletImageSupplier.SUPPLIER.getImageWidth() / 2;
        int by = tank.getY() + tank.getHeight() / 2 - GeneralBulletImageSupplier.SUPPLIER.getImageHeight() / 2;
        TGC.getTGC().addGameObj(new Bullet(bx, by, tank.getDirection(), tank.getGroup(), GeneralBulletImageSupplier.SUPPLIER));

        fireSound(tank);
    }
}
