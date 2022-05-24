package redcoder.tank.collider;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Boom;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;

/**
 * 处理子弹与坦克相撞的碰撞器
 */
public class BulletTankCollider extends ColliderBase {

    public BulletTankCollider() {
        super("BulletTankCollider");
    }

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;

            if (bullet.getGroup() != tank.getGroup()
                    && bullet.getRectangle().intersects(tank.getRectangle())) {
                bullet.die();
                tank.die();

                int boomX = tank.getX() + tank.getWidth() / 2 - Boom.WIDTH / 2;
                int boomY = tank.getY() + tank.getHeight() / 2 - Boom.HEIGHT / 2;
                TGC.getTGC().addGameObj(new Boom(boomX, boomY));

                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
