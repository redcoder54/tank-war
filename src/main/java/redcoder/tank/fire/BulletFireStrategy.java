package redcoder.tank.fire;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.TankGame;

import static redcoder.tank.ResourceManager.*;

/**
 * 往坦克前进方向发射一颗普通的子弹
 */
public class BulletFireStrategy extends VocalFireStrategySupport implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - bulletL.getWidth() / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - bulletL.getHeight() / 2;

        TankGame tankGame = tank.getTankGame();
        tankGame.getGameObjs().add(new Bullet(bx, by, tank.getDirection(), tank.getGroup(), tankGame,
                bulletL, bulletU, bulletR, bulletD));

        fireSound(tank);
    }
}
