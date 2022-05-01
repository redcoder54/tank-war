package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 发射一颗导弹
 */
public class MissileFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        TankFrame tankFrame = tank.getTankFrame();
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.getBullets().add(new Bullet(bx, by, tank.getDirection(), tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));
    }
}
