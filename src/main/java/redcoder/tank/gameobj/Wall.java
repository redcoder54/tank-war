package redcoder.tank.gameobj;

import redcoder.tank.model.GameModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObj {

    private Rectangle rectangle;

    public Wall(int x, int y) {
        super(x, y);
        this.rectangle = new Rectangle(x, y, 60, 60);
    }

    @Override
    public void paint(Graphics g, GameModel gameModel) {
        g.drawImage(WALL, x, y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public static BufferedImage WALL;
    static {
        try {
            ClassLoader classLoader = Boom.class.getClassLoader();
            WALL = ImageIO.read(classLoader.getResourceAsStream(String.format("images/obstacle/wall.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
