package redcoder.tank.move;

import redcoder.tank.TankPanel;
import redcoder.tank.gameobj.Bullet;

public class BulletMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Bullet> {

    @Override
    public void move(Bullet bullet) {
        int x = moveX(bullet.getX(), bullet.getSpeed(), bullet.getDirection());
        int y = moveY(bullet.getY(), bullet.getSpeed(), bullet.getDirection());
        bullet.setX(x);
        bullet.setY(y);

        if (x < 0 || y < 0 || x > TankPanel.WIDTH || y > TankPanel.HEIGHT) {
            bullet.die();
        }

        // update rectangle
        bullet.getRectangle().x = x;
        bullet.getRectangle().y = y;
    }
}
