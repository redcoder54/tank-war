package redcoder.tank.collider;

import redcoder.tank.GameModel;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.Wall;

/**
 * 处理坦克与墙相撞的碰撞器
 */
public class TankWallCollider extends ColliderBase {

    public TankWallCollider() {
        super("TankWallCollider");
    }

    @Override
    public boolean collide(GameModel gameModel, GameObj o1, GameObj o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;

            if (tank.getRectangle().intersects(wall.getRectangle())) {
                // 回退到原来的位置
                tank.back();
                return false;
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(gameModel, o2, o1);
        }
        return true;
    }
}
