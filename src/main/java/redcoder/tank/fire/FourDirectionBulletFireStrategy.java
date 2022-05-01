package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Direction;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;
import static redcoder.tank.ResourceManager.bulletD;

/**
 * 往四个方向射击子弹
 */
public class FourDirectionBulletFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        TankFrame tankFrame = tank.getTankFrame();
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tankFrame.getBullets().add(new Bullet(bx, by, Direction.LEFT, tank.getGroup(), tankFrame,
                bulletL, bulletU, bulletR, bulletD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.UP, tank.getGroup(), tankFrame,
                bulletL, bulletU, bulletR, bulletD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.RIGHT, tank.getGroup(), tankFrame,
                bulletL, bulletU, bulletR, bulletD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.DOWN, tank.getGroup(), tankFrame,
                bulletL, bulletU, bulletR, bulletD));
    }
}
