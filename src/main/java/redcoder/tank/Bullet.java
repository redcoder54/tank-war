package redcoder.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    public static final int SPEED = 20;

    private int x, y;
    private final Direction direction;
    private boolean living = true;
    private Group group;
    private TankFrame tankFrame;
    private Rectangle rectangle;
    // 四个方向的子弹图像
    private BufferedImage left;
    private BufferedImage up;
    private BufferedImage right;
    private BufferedImage down;

    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame,
                  BufferedImage left, BufferedImage up, BufferedImage right, BufferedImage down) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        this.rectangle = new Rectangle(x, y, left.getWidth(), left.getHeight());
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

    public void move() {
        switch (direction) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || y < 0 || x > tankFrame.getWidth() || y > tankFrame.getHeight()) {
            living = false;
        }

        // update rectangle
        rectangle.x = x;
        rectangle.y = y;
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

    public boolean isLiving() {
        return living;
    }

    public Group getGroup() {
        return group;
    }

    public void setLeft(BufferedImage left) {
        this.left = left;
    }

    public void setUp(BufferedImage up) {
        this.up = up;
    }

    public void setRight(BufferedImage right) {
        this.right = right;
    }

    public void setDown(BufferedImage down) {
        this.down = down;
    }
}
