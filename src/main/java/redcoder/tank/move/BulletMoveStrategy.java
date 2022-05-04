package redcoder.tank.move;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.TGC;

public class BulletMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Bullet> {

    @Override
    public void move(Bullet bullet) {
        int x = moveX(bullet.getX(), bullet.getSpeed(), bullet.getDirection());
        int y = moveY(bullet.getY(), bullet.getSpeed(), bullet.getDirection());
        bullet.setX(x);
        bullet.setY(y);

        TGC tgc = TGC.getTGC();
        if (x < 0 || y < 0 || x > tgc.getWidth() || y > tgc.getHeight()) {
            bullet.die();
        }

        // update rectangle
        bullet.getRectangle().x = x;
        bullet.getRectangle().y = y;
    }
}
