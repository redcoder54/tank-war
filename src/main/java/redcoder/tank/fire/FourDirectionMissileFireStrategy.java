package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Direction;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 往四个方向发射导弹
 */
public class FourDirectionMissileFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        TankFrame tankFrame = tank.getTankFrame();
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tankFrame.getBullets().add(new Bullet(bx, by, Direction.LEFT, tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.UP, tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.RIGHT, tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));
        tankFrame.getBullets().add(new Bullet(bx, by, Direction.DOWN, tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));
    }
}
