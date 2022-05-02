package redcoder.tank.collider;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.GameObj;

/**
 * 处理子弹与子弹相撞的碰撞器
 */
public class BulletBulletCollider implements Collider {

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        if (o1 instanceof Bullet && o2 instanceof Bullet) {
            Bullet bullet1 = (Bullet) o1;
            Bullet bullet2 = (Bullet) o2;

            if (bullet1.getGroup() != bullet2.getGroup()
                    && bullet1.getRectangle().intersects(bullet2.getRectangle())) {
                bullet1.die();
                bullet2.die();

                return false;
            }
        }
        return true;
    }
}
