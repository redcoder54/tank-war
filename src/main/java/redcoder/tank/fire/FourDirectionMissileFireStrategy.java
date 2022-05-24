package redcoder.tank.fire;

import redcoder.tank.Direction;
import redcoder.tank.GameModel;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.MissileImageSupplier;

/**
 * 往四个方向发射导弹
 */
public class FourDirectionMissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(GameModel gameModel, Tank tank) {
        // FIXME: 2022/5/23
        int bY = tank.getY() + tank.getWidth() / 2 - MissileImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bX = tank.getX() + tank.getHeight() / 2 - MissileImageSupplier.SUPPLIER.getImageHeight() / 2;
        for (Direction direction : Direction.values()) {
            gameModel.addGameObj(new Bullet(bX, bY, direction, tank.getGroup(),MissileImageSupplier.SUPPLIER));
        }

        fireSound(tank);
    }
}
