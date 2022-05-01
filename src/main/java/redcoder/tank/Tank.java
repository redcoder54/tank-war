package redcoder.tank;

import java.awt.*;
import java.util.Random;

import static redcoder.tank.Direction.DIRECTIONS;

public class Tank {

    public static final int DEFAULT_SPEED = 10;
    public static final int DEFAULT_STEP = 20;
    public static final int WIDTH = ResourceManager.goodTankL.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankL.getHeight();

    private int x;
    private int y;
    private int speed;
    private boolean moving;
    private Direction direction;
    private Group group;
    private TankFrame tankFrame;
    private boolean robot;

    private boolean living = true;
    private int step = DEFAULT_STEP;
    private Random random;

    public Tank(int x, int y, boolean moving, Direction direction, Group group, TankFrame tankFrame) {
        this(x, y, DEFAULT_SPEED, moving, direction, group, tankFrame, false);
    }

    public Tank(int x, int y, int speed, boolean moving, Direction direction, Group group, TankFrame tankFrame, boolean robot) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = moving;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        this.robot = robot;
        random = new Random();
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.getEnemyTanks().remove(this);
            return;
        }


        switch (direction) {
            case LEFT:
                g.drawImage(group == Group.GOOD ? ResourceManager.goodTankL : ResourceManager.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.GOOD ? ResourceManager.goodTankR : ResourceManager.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.GOOD ? ResourceManager.goodTankU : ResourceManager.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.GOOD ? ResourceManager.goodTankD : ResourceManager.badTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    public void move() {
        if (!moving) return;

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }

        if (robot) {
            // 撞墙检测
            if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
                direction = Direction.getOppositeDirection(direction);
            }

            if (step-- <= 0) {
                direction = DIRECTIONS[random.nextInt(4)];
                step = DEFAULT_STEP;
            }

            if (random.nextInt(101) > 98) fire();
        }
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.getBullets().add(new Bullet(bx, by, direction, group, tankFrame));
    }

    public void die() {
        this.living = false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDir(Direction direction) {
        this.direction = direction;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

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

    public Group getGroup() {
        return group;
    }
}
