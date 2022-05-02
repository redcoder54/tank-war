package redcoder.tank.fire;

import redcoder.tank.TankGame;
import redcoder.tank.gameobj.Bullet;
import redcoder.tank.gameobj.Tank;

import java.awt.image.BufferedImage;
import java.util.Random;

import static redcoder.tank.ResourceManager.emoji;

/**
 * emoji表情包子弹
 */
public class EmojiFireStrategy implements FireStrategy {

    private final Random random = new Random();
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - HEIGHT / 2;

        int i = random.nextInt(emoji.length);
        BufferedImage image = emoji[i];

        TankGame.getInstance().addGameObj(new Bullet(bx, by, tank.getDirection(), tank.getGroup(),
                image, image, image, image));
    }
}
