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

    public Bullet(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living) {
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup())
            return false;

        Rectangle rec1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rec2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rec1.intersects(rec2)) {
            // 碰撞
            this.die();
            tank.die();
            tankFrame.getBooms().add(new Boom(tank.getX(), tank.getY()));
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
