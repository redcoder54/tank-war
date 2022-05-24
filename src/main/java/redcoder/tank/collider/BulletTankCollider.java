package redcoder.tank.collider;

import redcoder.tank.gameobj.Boom;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.model.GameModel;

/**
 * 处理子弹与坦克相撞的碰撞器
 */
public class BulletTankCollider extends ColliderBase {

    public BulletTankCollider() {
        super("BulletTankCollider");
    }

    @Override
    public boolean collide(GameModel gameModel, GameObj o1, GameObj o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;

            if (bullet.getGroup() != tank.getGroup()
                    && bullet.getRectangle().intersects(tank.getRectangle())) {
                bullet.die();
                tank.die(gameModel);

                int boomX = tank.getX() + tank.getWidth() / 2 - 35;
                int boomY = tank.getY() + tank.getHeight() / 2 - 50;
                gameModel.addGameObj(new Boom(boomX, boomY));

                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(gameModel, o2, o1);
        }
        return true;
    }
}
