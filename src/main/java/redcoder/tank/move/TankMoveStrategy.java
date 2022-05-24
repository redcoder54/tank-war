package redcoder.tank.move;

import redcoder.tank.gameobj.Direction;
import redcoder.tank.gameobj.Group;
import redcoder.tank.TankPanel;
import redcoder.tank.gameobj.Tank;

import java.util.Random;

public class TankMoveStrategy extends CoordinateMoveStrategySupport implements MoveStrategy<Tank> {

    private static final int DEFAULT_DIRECTION_STEP = 20;
    private int directionStep = DEFAULT_DIRECTION_STEP;
    private final Random random = new Random();

    @Override
    public void move(Tank tank) {
        if (!tank.isMoving()) {
            return;
        }

        int x = moveX(tank.getX(), tank.getSpeed(), tank.getDirection());
        int y = moveY(tank.getY(), tank.getSpeed(), tank.getDirection());
        tank.setX(x);
        tank.setY(y);

        if (tank.getGroup() == Group.BAD) {
            // 随机开火
            if (random.nextInt(100) > 97) tank.fire();

            // 前进干步后，改变方向
            if (--directionStep <= 0) {
                tank.setDirection(Direction.values()[random.nextInt(4)]);
                directionStep = DEFAULT_DIRECTION_STEP;
            }
        }

        // 边界检查，防止跑出屏幕
        boundaryCheck(tank);

        // update rectangle
        tank.getRectangle().x = tank.getX();
        tank.getRectangle().y = tank.getY();

        // 玩家坦克移动音效
        // if (tank.getGroup() == Group.GOOD) {
        //     new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
        // }
    }

    private void boundaryCheck(Tank tank) {
        int x = tank.getX();
        int y = tank.getY();
        int width = tank.getWidth();
        int height = tank.getHeight();

        if (x < 0) {
            tank.setX(0);
        }
        if (x > TankPanel.WIDTH - width) {
            tank.setX(TankPanel.WIDTH - width);
        }
        if (y < 0) {
            tank.setY(0);
        }
        if (y > TankPanel.HEIGHT - height) {
            tank.setY(TankPanel.HEIGHT - height);
        }
    }
}
