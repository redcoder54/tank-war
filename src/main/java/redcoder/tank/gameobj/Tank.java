package redcoder.tank.gameobj;

import redcoder.tank.*;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.move.MoveStrategy;
import redcoder.tank.move.TankMoveStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends GameObj {

    public static final int WIDTH = ImageResource.goodTank1L.getWidth();
    public static final int HEIGHT = ImageResource.goodTank1L.getHeight();

    private static final FireStrategy PLAYER_FIRE_STRATEGY;
    private static final FireStrategy ENEMY_FIRE_STRATEGY;

    static {
        PLAYER_FIRE_STRATEGY = GameConfigFactory.getGameConfig().getPlayerFireStrategy();
        ENEMY_FIRE_STRATEGY = GameConfigFactory.getGameConfig().getEnemyFireStrategy();
    }

    private int speed;
    private Direction direction;
    private Group group;
    private boolean moving;

    private boolean living = true;
    private Rectangle rectangle;
    private FireStrategy fireStrategy;
    private MoveStrategy<Tank> moveStrategy;

    private int oldX;
    private int oldY;

    private Random random = new Random();

    public Tank(int x, int y, int speed, Direction direction, Group group, boolean moving) {
        super(x, y);
        this.oldX = x;
        this.oldY = y;

        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.moving = moving;

        init();
    }

    private void init() {
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);

        if (group == Group.GOOD) {
            fireStrategy = PLAYER_FIRE_STRATEGY;
        } else {
            fireStrategy = ENEMY_FIRE_STRATEGY;
            TGC.getTGC().getGameProgress().livingTankCountIncr();
        }

        this.moveStrategy = new TankMoveStrategy();

    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            TGC.getTGC().removeGameObj(this);
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
                    image = random.nextInt(10) >= 5 ? ImageResource.goodTank1L : ImageResource.goodTank2L;
                    break;
                case RIGHT:
                    image = random.nextInt(10) >= 5 ? ImageResource.goodTank1R : ImageResource.goodTank2R;
                    break;
                case UP:
                    image = random.nextInt(10) >= 5 ? ImageResource.goodTank1U : ImageResource.goodTank2U;
                    break;
                case DOWN:
                    image = random.nextInt(10) >= 5 ? ImageResource.goodTank1D : ImageResource.goodTank2D;
                    break;
                default:
                    break;
            }
        } else {
            switch (direction) {
                case LEFT:
                    image = random.nextInt(10) >= 5 ? ImageResource.badTank1L : ImageResource.badTank2L;
                    break;
                case RIGHT:
                    image = random.nextInt(10) >= 5 ? ImageResource.badTank1R : ImageResource.badTank2R;
                    break;
                case UP:
                    image = random.nextInt(10) >= 5 ? ImageResource.badTank1U : ImageResource.badTank2U;
                    break;
                case DOWN:
                    image = random.nextInt(10) >= 5 ? ImageResource.badTank1D : ImageResource.badTank2D;
                    break;
                default:
                    break;
            }
        }
        return image;
    }

    public void move() {
        if (pause) {
            return;
        }

        oldX = x;
        oldY = y;

        moveStrategy.move(this);
    }

    public void fire() {
        if (living && !pause) {
            fireStrategy.fire(this);
        }
    }

    public void die() {
        if (group == Group.BAD) {
            TGC.getTGC().getGameProgress().diedTankCountIncr();
        }
        this.living = false;
    }

    public boolean isLiving() {
        return living;
    }

    public void back() {
        this.x = oldX;
        this.y = oldY;
    }

    // -------------- getter setter

    public void setX(int x) {
        this.x = x;
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
        if (this.pause) {
            return;
        }
        this.direction = direction;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        if (this.pause) {
            return;
        }
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
