package redcoder.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 20;
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private int x, y;
    private final Direction direction;
    private boolean living = true;
    private Group group;
    private TankFrame tankFrame;
    private Rectangle rectangle;

    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.getBullets().remove(this);
            return;
        }

        switch (direction) {
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
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
}
