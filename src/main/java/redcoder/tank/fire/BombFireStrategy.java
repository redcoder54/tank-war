package redcoder.tank.fire;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.BombImageSupplier;

/**
 * 扔炸弹
 */
public class BombFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(GameModel gameModel, Tank tank) {
        int bY = tank.getY() + tank.getWidth() / 2 - BombImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bX = tank.getX() + tank.getHeight() / 2 - BombImageSupplier.SUPPLIER.getImageHeight() / 2;
        gameModel.addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(), BombImageSupplier.SUPPLIER));

        fireSound(tank);
    }
}
