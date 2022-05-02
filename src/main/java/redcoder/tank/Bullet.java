package redcoder.tank;

import redcoder.tank.move.BulletMoveStrategy;
import redcoder.tank.move.Movable;
import redcoder.tank.move.MoveStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet implements Movable {
    public static final int DEFAULT_SPEED = 20;

    private int x;
    private int y;
    private int speed;
    private final Direction direction;
    private Group group;
    private TankFrame tankFrame;
    // 四个方向的子弹图像
    private BufferedImage left;
    private BufferedImage up;
    private BufferedImage right;
    private BufferedImage down;

    private Rectangle rectangle;
    private boolean living;
    private MoveStrategy<Bullet> moveStrategy;

    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame,
                  BufferedImage left, BufferedImage up, BufferedImage right, BufferedImage down) {
        this(x, y, DEFAULT_SPEED, direction, group, tankFrame, left, up, right, down);
    }

    public Bullet(int x, int y, int speed, Direction direction, Group group, TankFrame tankFrame,
                  BufferedImage left, BufferedImage up, BufferedImage right, BufferedImage down) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        this.rectangle = new Rectangle(x, y, left.getWidth(), left.getHeight());
        this.living = true;
        this.moveStrategy = new BulletMoveStrategy();
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.getBullets().remove(this);
            return;
        }

        switch (direction) {
            case LEFT:
                g.drawImage(left, x, y, null);
                break;
            case RIGHT:
                g.drawImage(right, x, y, null);
                break;
            case UP:
                g.drawImage(up, x, y, null);
                break;
            case DOWN:
                g.drawImage(down, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    @Override
    public void move() {
        moveStrategy.move(this);
    }

    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return false;

        if (rectangle.intersects(tank.getRectangle())) {
            // 碰撞
            this.die();
            tank.die();

            int boomX = tank.getX() + Tank.WIDTH / 2 - Boom.WIDTH / 2;
            int boomY = tank.getY() + Tank.HEIGHT / 2 - Boom.HEIGHT / 2;
            tankFrame.getBooms().add(new Boom(boomX, boomY, tankFrame));

            return true;
        }
        return false;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
