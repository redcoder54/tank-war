package redcoder.tank.collider;

import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Wall;

public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;

            if (bullet.getRectangle().intersects(wall.getRectangle())) {
                bullet.die();
            }
            return false;
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return false;
    }
}
