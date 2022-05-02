package redcoder.tank.fire;

import redcoder.tank.TankGame;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;

import static redcoder.tank.ResourceManager.*;

/**
 * 核弹
 */
public class NuClearBombFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - nuclearBombL.getWidth() / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - nuclearBombL.getHeight() / 2;

        TankGame.getInstance().addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(),
                nuclearBombL, nuclearBombU, nuclearBombR, nuclearBombD));
    }
}
