package redcoder.tank;

import redcoder.tank.fire.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class Tank {

    public static final int DEFAULT_SPEED = 10;
    public static final int DEFAULT_DIRECTION_STEP = 20;
    public static final int DEFAULT_COLOR_STEP = 5;
    public static final int WIDTH = ResourceManager.goodTank1L.getWidth();
    public static final int HEIGHT = ResourceManager.goodTank1L.getHeight();

    private int x;
    private int y;
    private int speed;
    private boolean moving;
    private Direction direction;
    private Group group;
    private TankFrame tankFrame;
    private FireStrategy fireStrategy;

    private boolean living = true;
    private int directionStep = DEFAULT_DIRECTION_STEP;
    private int colorStep = DEFAULT_COLOR_STEP;
    private Random random;
    private Rectangle rectangle;

    public Tank(int x, int y, int speed, boolean moving, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = moving;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;

        init();
    }

    private void init() {
        random = new Random();
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);

        String type = ConfigManager.getInstance().getFireStrategy();
        if (Objects.equals(FireStrategy.BULLET, type)) {
            fireStrategy = new BulletFireStrategy();
        }else if (Objects.equals(FireStrategy.FOUR_DIRECTION_BULLET, type)) {
            fireStrategy = new FourDirectionBulletFireStrategy();
        }else if (Objects.equals(FireStrategy.MISSILE, type)) {
            fireStrategy = new MissileFireStrategy();
        } else if (Objects.equals(FireStrategy.FOUR_DIRECTION_MISSILE, type)) {
            fireStrategy = new FourDirectionMissileFireStrategy();
        } else {
            fireStrategy = new BulletFireStrategy();
        }
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.getAiTanks().remove(this);
            return;
        }

        g.drawImage(getTankImage(), x, y, null);

        move();
    }

    private BufferedImage getTankImage() {
        BufferedImage image = null;
        if (group == Group.GOOD) {
            switch (direction) {
                case LEFT:
                    image = random.nextInt(10) >= 5 ? ResourceManager.goodTank1L : ResourceManager.goodTank2L;
                    break;
                case RIGHT:
                    image = random.nextInt(10) >= 5 ? ResourceManager.goodTank1R : ResourceManager.goodTank2R;
                    break;
                case UP:
                    image = random.nextInt(10) >= 5 ? ResourceManager.goodTank1U : ResourceManager.goodTank2U;
                    break;
                case DOWN:
                    image = random.nextInt(10) >= 5 ? ResourceManager.goodTank1D : ResourceManager.goodTank2D;
                    break;
                default:
                    break;
            }
        } else {
            switch (direction) {
                case LEFT:
                    image = random.nextInt(10) >= 5 ? ResourceManager.badTank1L : ResourceManager.badTank2L;
                    break;
                case RIGHT:
                    image = random.nextInt(10) >= 5 ? ResourceManager.badTank1R : ResourceManager.badTank2R;
                    break;
                case UP:
                    image = random.nextInt(10) >= 5 ? ResourceManager.badTank1U : ResourceManager.badTank2U;
                    break;
                case DOWN:
                    image = random.nextInt(10) >= 5 ? ResourceManager.badTank1D : ResourceManager.badTank2D;
                    break;
                default:
                    break;
            }
        }
        return image;
    }

    private void move() {
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

        if (group == Group.BAD) {
            // 随机开火
            if (random.nextInt(100) > 97) fire();

            // 前进干步后，改变方向
            if (--directionStep <= 0) {
                direction = Direction.values()[random.nextInt(4)];
                directionStep = DEFAULT_DIRECTION_STEP;
            }
        }

        // 边界检查，防止跑出屏幕
        boundaryCheck();

        // update rectangle
        rectangle.x = x;
        rectangle.y = y;
    }

    private void boundaryCheck() {
        if (x < 0) x = 0;
        if (x > tankFrame.getWidth() - Tank.WIDTH) x = tankFrame.getWidth() - Tank.WIDTH;
        if (y < 30) y = 30;
        if (y > tankFrame.getHeight() - Tank.HEIGHT) y = tankFrame.getHeight() - Tank.HEIGHT;
    }

    public void fire() {
        fireStrategy.fire(this);
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }
}
