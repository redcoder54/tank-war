package redcoder.tank.fire;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.bullet.EmojiImageSupplier;

/**
 * emoji表情包子弹
 */
public class EmojiFireStrategy implements FireStrategy {

    @Override
    public void fire(GameModel gameModel, Tank tank) {
        int bx = tank.getX() + tank.getWidth() / 2 - EmojiImageSupplier.SUPPLIER.getImageWidth() / 2;
        int by = tank.getY() + tank.getHeight() / 2 - EmojiImageSupplier.SUPPLIER.getImageHeight() / 2;
        gameModel.addGameObj(new Bullet(bx, by, tank.getDirection(), tank.getGroup(), EmojiImageSupplier.SUPPLIER));
    }
}
