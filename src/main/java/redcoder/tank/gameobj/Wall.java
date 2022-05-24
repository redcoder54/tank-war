package redcoder.tank.gameobj;

import redcoder.tank.ImageResource;
import redcoder.tank.GameModel;

import java.awt.*;

public class Wall extends GameObj {


    public static final int WIDTH = ImageResource.wall.getWidth();
    public static final int HEIGHT = ImageResource.wall.getHeight();

    private Rectangle rectangle;

    public Wall(int x, int y) {
        super(x, y);

        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g, GameModel gameModel) {
        g.drawImage(ImageResource.wall, x, y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
