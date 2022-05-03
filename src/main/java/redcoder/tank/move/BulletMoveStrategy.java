package redcoder.tank.move;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.TankGameContext;

public class BulletMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Bullet> {

    @Override
    public void move(Bullet bullet) {
        int x = moveX(bullet.getX(), bullet.getSpeed(), bullet.getDirection());
        int y = moveY(bullet.getY(), bullet.getSpeed(), bullet.getDirection());
        bullet.setX(x);
        bullet.setY(y);

        TankGameContext tankGameContext = TankGameContext.getTankGameContext();
        if (x < 0 || y < 0 || x > tankGameContext.getWidth() || y > tankGameContext.getHeight()) {
            bullet.die();
        }

        // update rectangle
        bullet.getRectangle().x = x;
        bullet.getRectangle().y = y;
    }
}
