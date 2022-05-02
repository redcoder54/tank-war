package redcoder.tank.collider;

import redcoder.tank.*;
import redcoder.tank.gameobj.Boom;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;

/**
 * 处理子弹与坦克相撞的碰撞器
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;

            if (bullet.getGroup() != tank.getGroup()
                    && bullet.getRectangle().intersects(tank.getRectangle())) {
                bullet.die();
                tank.die();

                int boomX = tank.getX() + Tank.WIDTH / 2 - Boom.WIDTH / 2;
                int boomY = tank.getY() + Tank.HEIGHT / 2 - Boom.HEIGHT / 2;
                TankGame.getInstance().addGameObj(new Boom(boomX, boomY));

                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
