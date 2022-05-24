package redcoder.tank.fire;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.NuClearBombImageSupplier;

/**
 * 核弹
 */
public class NuClearBombFireStrategy implements FireStrategy {

    @Override
    public void fire(GameModel gameModel, Tank tank) {
        int bX = tank.getX() + tank.getWidth() / 2 - NuClearBombImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bY = tank.getY() + tank.getHeight() / 2 - NuClearBombImageSupplier.SUPPLIER.getImageHeight() / 2;
        gameModel.addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(), NuClearBombImageSupplier.SUPPLIER));
    }
}
