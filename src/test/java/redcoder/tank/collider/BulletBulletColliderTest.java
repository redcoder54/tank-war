package redcoder.tank.collider;

import org.junit.Assert;
import org.junit.Test;
import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.gameobj.Bullet;

import static redcoder.tank.ResourceManager.*;

public class BulletBulletColliderTest {
    BulletBulletCollider collider = new BulletBulletCollider();

    @Test
    public void collide() {
        Bullet b1 = new Bullet(0, 0, Direction.LEFT, Group.GOOD, bulletL, bulletU, bulletR, bulletD);
        Bullet b2 = new Bullet(0, 0, Direction.LEFT, Group.BAD, bulletL, bulletU, bulletR, bulletD);
        Assert.assertFalse(collider.collide(b1, b2));
    }
}
