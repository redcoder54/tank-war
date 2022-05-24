package redcoder.tank.fire;

import redcoder.tank.Direction;
import redcoder.tank.GameModel;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.BombImageSupplier;

/**
 * 往四个方向扔炸弹
 */
public class FourDirectionBombFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(GameModel gameModel, Tank tank) {
        int bY = tank.getY() + tank.getWidth() / 2 - BombImageSupplier.SUPPLIER.getImageWidth() / 2;
        int bX = tank.getX() + tank.getHeight() / 2 - BombImageSupplier.SUPPLIER.getImageHeight() / 2;
        for (Direction direction : Direction.values()) {
            gameModel.addGameObj(new Bullet(bX, bY, direction, tank.getGroup(), BombImageSupplier.SUPPLIER));
        }

        fireSound(tank);
    }
}
