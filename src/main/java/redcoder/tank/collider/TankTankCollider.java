package redcoder.tank.collider;

import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;

/**
 * 处理坦克与坦克相撞的碰撞器
 */
public class TankTankCollider implements Collider{

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;

            if (tank1.getRectangle().intersects(tank2.getRectangle())) {
                // 回退到原来的位置
                tank1.backoff();
                tank2.backoff();
            }
        }
        return true;
    }
}
