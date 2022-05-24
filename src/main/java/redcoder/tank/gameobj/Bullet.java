package redcoder.tank.gameobj;

import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.TGC;
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
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            TGC.getTGC().removeGameObj(this);
            return;
        }

        int width = imageSupplier.getImageWidth();
        int height = imageSupplier.getImageHeight();
        g.drawImage(getImage(), x, y, width, height, null);

        move();
    }

    private Image getImage() {
        Image image = null;
        switch (direction) {
            case LEFT:
                if (imageSupplier instanceof DirectionalImageSupplier) {
                    image = ((DirectionalImageSupplier) imageSupplier).getLeftImage();
                } else {
                    image = imageSupplier.getImage();
                }
                break;
            case RIGHT:
                if (imageSupplier instanceof DirectionalImageSupplier) {
                    image = ((DirectionalImageSupplier) imageSupplier).getRightImage();
                } else {
                    image = imageSupplier.getImage();
                }
                break;
            case UP:
                if (imageSupplier instanceof DirectionalImageSupplier) {
                    image = ((DirectionalImageSupplier) imageSupplier).getUpImage();
                } else {
                    image = imageSupplier.getImage();
                }
                break;
            case DOWN:
                if (imageSupplier instanceof DirectionalImageSupplier) {
                    image = ((DirectionalImageSupplier) imageSupplier).getDownImage();
                } else {
                    image = imageSupplier.getImage();
                }
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
