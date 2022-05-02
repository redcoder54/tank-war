package redcoder.tank.gameobj;

import redcoder.tank.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Wall extends GameObj {

    public static final int WIDTH = ResourceManager.squares[0].getWidth() * 3;
    public static final int HEIGHT = ResourceManager.squares[0].getHeight() * 3;

    private Random random;
    private Rectangle rectangle;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;

        this.random = new Random();
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        int i = random.nextInt(ResourceManager.squares.length);
        g.drawImage(ResourceManager.squares[i], x, y, WIDTH, HEIGHT, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
