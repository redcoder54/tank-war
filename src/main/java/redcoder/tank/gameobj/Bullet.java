package redcoder.tank.gameobj;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.gameobj.image.ImageSupplier;
import redcoder.tank.move.BulletMoveStrategy;
import redcoder.tank.move.MoveStrategy;

import java.awt.*;

public class Bullet extends GameObj {
    public static final int DEFAULT_SPEED = 25;

    private int speed;
    private final Direction direction;
    private Group group;
    private ImageSupplier imageSupplier;
    private Rectangle rectangle;
    private boolean living;
    private MoveStrategy<Bullet> moveStrategy;

    private transient Image up;
    private transient Image left;
    private transient Image right;
    private transient Image down;


    /**
     * 创建子弹
     *
     * @param x             x轴坐标
     * @param y             y轴坐标
     * @param direction     子弹的方向
     * @param group         子弹所属的组{@link Group}
     * @param imageSupplier 子弹图像提供者
     */
    public Bullet(int x, int y, Direction direction, Group group, ImageSupplier imageSupplier) {
        this(x, y, DEFAULT_SPEED, direction, group, imageSupplier);
    }

    /**
     * 创建子弹
     *
     * @param x             x轴坐标
     * @param y             y轴坐标
     * @param speed         子弹的速度
     * @param direction     子弹的方向
     * @param group         子弹所属的组{@link Group}
     * @param imageSupplier 子弹图像提供者
     */
    public Bullet(int x, int y, int speed, Direction direction, Group group, ImageSupplier imageSupplier) {
        super(x, y);
        this.speed = speed;
        this.direction = direction;
        this.group = group;
        this.imageSupplier = imageSupplier;
        this.rectangle = new Rectangle(x, y, imageSupplier.getImageWidth(), imageSupplier.getImageHeight());
        this.living = true;
        this.moveStrategy = new BulletMoveStrategy();

        if (imageSupplier instanceof DirectionalImageSupplier) {
            DirectionalImageSupplier supplier = (DirectionalImageSupplier) imageSupplier;
            up = (supplier).getUpImage();
            left = (supplier).getLeftImage();
            right = (supplier).getRightImage();
            down = (supplier).getDownImage();
        } else {
            up = imageSupplier.getImage();
            left = imageSupplier.getImage();
            right = imageSupplier.getImage();
            down = imageSupplier.getImage();
        }
    }

    @Override
    public void paint(Graphics g, GameModel gameModel) {
        if (!living) {
            gameModel.removeGameObj(this);
            gameModel.getListeners().removePauseResumeListener(prIndex);
            return;
        }

        int width = imageSupplier.getImageWidth();
        int height = imageSupplier.getImageHeight();
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
        if (pause) {
            return;
        }
        moveStrategy.move(this);
    }

    public void die() {
        this.living = false;
    }

    // -------- getter setter method
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

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
