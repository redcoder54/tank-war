package redcoder.tank.move;

import redcoder.tank.Direction;

public class CoordinateMoveStrategySupport {

    protected int moveX(int x, int speed, Direction direction) {
        switch (direction) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
        return x;
    }

    protected int moveY(int y, int speed, Direction direction) {
        switch (direction) {
            case DOWN:
                y += speed;
                break;
            case UP:
                y -= speed;
                break;
            default:
                break;
        }
        return y;
    }
}
