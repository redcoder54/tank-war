package redcoder.tank.fire;

import redcoder.tank.TankGame;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;

import static redcoder.tank.ResourceManager.*;

public class NuClearBombFireStrategy implements FireStrategy {

    private static final int NUCLEAR_BOMB_WIDTH = nuclearBombL.getWidth() / 4;
    private static final int NUCLEAR_BOMB_HEIGHT = nuclearBombL.getHeight() / 4;

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - NUCLEAR_BOMB_WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - NUCLEAR_BOMB_HEIGHT / 2;

        TankGame.getInstance().addGameObj(new Bullet(bX, bY, tank.getDirection(), tank.getGroup(),
                nuclearBombL, nuclearBombU, nuclearBombR, nuclearBombD, NUCLEAR_BOMB_WIDTH, NUCLEAR_BOMB_HEIGHT));
    }
}
