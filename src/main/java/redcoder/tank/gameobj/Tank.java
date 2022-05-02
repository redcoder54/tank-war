package redcoder.tank.gameobj;

import redcoder.tank.*;
import redcoder.tank.fire.BulletFireStrategy;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.move.MoveStrategy;
import redcoder.tank.move.TankMoveStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends GameObj {

    public static final int WIDTH = ResourceManager.goodTank1L.getWidth();
    public static final int HEIGHT = ResourceManager.goodTank1L.getHeight();

    private int speed;
    private Direction direction;
    private Group group;
    private boolean moving;

    private boolean living = true;
    private Random random;
    private Rectangle rectangle;
    private FireStrategy fireStrategy;
    private MoveStrategy<Tank> moveStrategy;

    private int oldX;
    private int oldY;

    public Tank(int x, int y, int speed, Direction direction, Group group, boolean moving) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.moving = moving;

        init();
    }

    private void init() {
        random = new Random();
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);

        try {
            Class<FireStrategy> clazz;
            if (group == Group.GOOD) {
                clazz = (Class<FireStrategy>) Class.forName(ConfigManager.getInstance().getPlayerFireStrategy());
            } else {
                clazz = (Class<FireStrategy>) Class.forName(ConfigManager.getInstance().getEnemyFireStrategy());
            }
            fireStrategy = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            fireStrategy = new BulletFireStrategy();
        }
        this.moveStrategy = new TankMoveStrategy();
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            TankGame.getInstance().removeGameObj(this);
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

    public void move() {
        oldX = x;
        oldY = y;

        moveStrategy.move(this);
    }

    public void fire() {
        if (living) {
            fireStrategy.fire(this);
        }
    }

    public void die() {
        this.living = false;
    }

    public void stop() {
        this.moving = false;
    }

    public void back() {
        this.x = oldX;
        this.y = oldY;
    }

    // -------------- getter setter

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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }
}
