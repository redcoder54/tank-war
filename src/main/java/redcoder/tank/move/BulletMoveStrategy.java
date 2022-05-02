package redcoder.tank.move;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.TankGame;

public class BulletMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Bullet> {

    @Override
    public void move(Bullet bullet) {
        int x = moveX(bullet.getX(), bullet.getSpeed(), bullet.getDirection());
        int y = moveY(bullet.getY(), bullet.getSpeed(), bullet.getDirection());
        bullet.setX(x);
        bullet.setY(y);

        TankGame tankGame = TankGame.getInstance();
        if (x < 0 || y < 0 || x > tankGame.getWidth() || y > tankGame.getHeight()) {
            bullet.die();
        }

        // update rectangle
        bullet.getRectangle().x = x;
        bullet.getRectangle().y = y;
    }
}
