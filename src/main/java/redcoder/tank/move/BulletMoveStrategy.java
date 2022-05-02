package redcoder.tank.move;

import redcoder.tank.Bullet;
import redcoder.tank.TankFrame;

public class BulletMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Bullet> {

    @Override
    public void move(Bullet bullet) {
        int x = moveX(bullet.getX(), bullet.getSpeed(), bullet.getDirection());
        int y = moveY(bullet.getY(), bullet.getSpeed(), bullet.getDirection());
        bullet.setX(x);
        bullet.setY(y);

        TankFrame tankFrame = bullet.getTankFrame();
        if (x < 0 || y < 0 || x > tankFrame.getWidth() || y > tankFrame.getHeight()) {
            bullet.die();
        }

        // update rectangle
        bullet.getRectangle().x = x;
        bullet.getRectangle().y = y;
    }
}
