package redcoder.tank.gameobj;

import redcoder.tank.ResourceManager;

import java.awt.*;

public class Wall extends GameObj {


    public static final int WIDTH = ResourceManager.wall.getWidth();
    public static final int HEIGHT = ResourceManager.wall.getHeight();

    private Rectangle rectangle;

    public Wall(int x, int y) {
        super(x, y);

        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.wall, x, y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
