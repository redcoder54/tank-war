package redcoder.tank.collider;

import org.junit.Assert;
import org.junit.Test;
import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.image.bullet.GeneralBulletImageSupplier;

public class BulletBulletColliderTest {
    BulletBulletCollider collider = new BulletBulletCollider();

    @Test
    public void collide() {
        Bullet b1 = new Bullet(0, 0, Direction.LEFT, Group.GOOD, GeneralBulletImageSupplier.SUPPLIER);
        Bullet b2 = new Bullet(0, 0, Direction.LEFT, Group.BAD, GeneralBulletImageSupplier.SUPPLIER);
        Assert.assertFalse(collider.collide(b1, b2));
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        Class<? extends BulletBulletCollider> clz = collider.getClass();
        BulletBulletCollider instance = clz.newInstance();
        Assert.assertNotNull(instance);
    }
}
