package redcoder.tank.fire;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.TankGameContext;

import static redcoder.tank.ResourceManager.*;

/**
 * 发射一颗导弹
 */
public class MissileFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bY = tank.getY() + Tank.HEIGHT / 2 - missileL.getHeight() / 2;
        int bX = tank.getX() + Tank.WIDTH / 2 - missileL.getWidth() / 2;

        TankGameContext.getTankGameContext().addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(),
                missileL, missileU, missileR, missileD));

        fireSound(tank);
    }
}
