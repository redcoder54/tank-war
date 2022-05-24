package redcoder.tank.gameobj;

import redcoder.tank.*;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.move.MoveStrategy;
import redcoder.tank.move.TankMoveStrategy;

import java.awt.*;

public class Tank extends GameObj {

    private int speed;
    private Direction direction;
    private Group group;
    private boolean moving;
    private DirectionalImageSupplier imageSupplier;
    private int width;
    private int height;
    private int oldX;
    private int oldY;
    private Rectangle rectangle;
    private FireStrategy fireStrategy;
    private MoveStrategy<Tank> moveStrategy;
    private boolean living = true;

    public Tank(int x, int y, int speed, Direction direction, Group group, boolean moving, DirectionalImageSupplier imageSupplier) {
        super(x, y);
        this.oldX = x;
        this.oldY = y;

        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.moving = moving;
        this.imageSupplier = imageSupplier;
        this.width = imageSupplier.getImageWidth();
        this.height = imageSupplier.getImageHeight();
        this.rectangle = new Rectangle(x, y, width, height);

        init();
    }

    private void init() {
        if (group == Group.GOOD) {
            fireStrategy = PLAYER_FIRE_STRATEGY;
        } else {
            fireStrategy = ENEMY_FIRE_STRATEGY;
            GameModelFactory.GameModelWrapper.getGameModel().getGameProgress().livingTankCountIncr();
        }

        this.moveStrategy = new TankMoveStrategy();
    }

    @Override
    public void paint(Graphics g, GameModel gameModel) {
        if (!living) {
            gameModel.removeGameObj(this);
            return;
        }

        g.drawImage(getTankImage(), x, y, null);

        move();
    }

    private Image getTankImage() {
        Image image = null;
        switch (direction) {
            case LEFT:
                image = imageSupplier.getLeftImage();
                break;
            case RIGHT:
                image = imageSupplier.getRightImage();
                break;
            case UP:
                image = imageSupplier.getUpImage();
                break;
            case DOWN:
                image = imageSupplier.getDownImage();
                break;
            default:
                break;
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
        GameModel gameModel = GameModelFactory.GameModelWrapper.getGameModel();
        if (living && !pause) {
            fireStrategy.fire(gameModel, this);
        }
    }

    public void die(GameModel gameModel) {
        if (group == Group.BAD) {
            gameModel.getGameProgress().diedTankCountIncr();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static final FireStrategy PLAYER_FIRE_STRATEGY;
    private static final FireStrategy ENEMY_FIRE_STRATEGY;

    static {
        PLAYER_FIRE_STRATEGY = GameConfigFactory.getGameConfig().getPlayerFireStrategy();
        ENEMY_FIRE_STRATEGY = GameConfigFactory.getGameConfig().getEnemyFireStrategy();
    }
}
