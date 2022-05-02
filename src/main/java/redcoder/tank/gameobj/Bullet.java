package redcoder.tank.gameobj;

import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.TankGame;
import redcoder.tank.move.BulletMoveStrategy;
import redcoder.tank.move.MoveStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObj {
    public static final int DEFAULT_SPEED = 20;

    private int speed;
    private final Direction direction;
    private Group group;
    // 四个方向的子弹图像
    private BufferedImage left;
    private BufferedImage up;
    private BufferedImage right;
    private BufferedImage down;

    private Rectangle rectangle;
    private boolean living;
    private MoveStrategy<Bullet> moveStrategy;

    private int width;
    private int height;

    public Bullet(int x, int y, Direction direction, Group group, BufferedImage left, BufferedImage up,
                  BufferedImage right, BufferedImage down) {
        this(x, y, DEFAULT_SPEED, direction, group, left, up, right, down, left.getWidth(), left.getHeight());
    }

    public Bullet(int x, int y, Direction direction, Group group, BufferedImage left, BufferedImage up,
                  BufferedImage right, BufferedImage down, int width, int height) {
        this(x, y, DEFAULT_SPEED, direction, group, left, up, right, down, width, height);
    }

    public Bullet(int x, int y, int speed, Direction direction, Group group, BufferedImage left, BufferedImage up,
                  BufferedImage right, BufferedImage down, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x, y, width, height);
        this.living = true;
        this.moveStrategy = new BulletMoveStrategy();
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            TankGame.getInstance().removeGameObj(this);
            return;
        }

        switch (direction) {
            case LEFT:
                g.drawImage(left, x, y, width, height, null);
                break;
            case RIGHT:
                g.drawImage(right, x, y, width, height, null);
                break;
            case UP:
                g.drawImage(up, x, y, width, height, null);
                break;
            case DOWN:
                g.drawImage(down, x, y, width, height, null);
                break;
            default:
                break;
        }
        move();
    }

    public void move() {
        moveStrategy.move(this);
    }

    public void die() {
        this.living = false;
    }

    // -------- getter setter method
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
