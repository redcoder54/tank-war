package redcoder.tank.gameobj;

import java.awt.*;

public class Wall extends GameObj {


    private int width;
    private int height;
    private Rectangle rectangle;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawRect(x, y, width, height);
        g.setColor(c);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
