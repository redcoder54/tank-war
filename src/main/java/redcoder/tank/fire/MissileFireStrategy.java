package redcoder.tank.fire;

import redcoder.tank.Bullet;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

import static redcoder.tank.ResourceManager.*;

/**
 * 发射一颗导弹
 */
public class MissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bY = tank.getY() + Tank.HEIGHT / 2 - missileL.getHeight() / 2;
        int bX = tank.getX() + Tank.WIDTH / 2 - missileL.getWidth() / 2;

        TankFrame tankFrame = tank.getTankFrame();
        tankFrame.getBullets().add(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(), tankFrame,
                missileL, missileU, missileR, missileD));

        fireSound(tank);
    }
}
