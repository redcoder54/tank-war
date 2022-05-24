package redcoder.tank.gameobj.image.bullet;

import redcoder.tank.gameobj.image.ImageSupplier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EmojiImageSupplier implements ImageSupplier {

    public final static EmojiImageSupplier SUPPLIER = new EmojiImageSupplier();

    private final Random random = new Random();

    @Override
    public Image getImage() {
        int i = random.nextInt(4);
        return emoji[i];
    }

    @Override
    public int getImageWidth() {
        return emoji[0].getWidth();
    }

    @Override
    public int getImageHeight() {
        return emoji[0].getHeight();
    }

    private static final BufferedImage[] emoji = new BufferedImage[4];

    static {
        try {
            ClassLoader classLoader = EmojiImageSupplier.class.getClassLoader();
            for (int i = 0; i < emoji.length; i++) {
                emoji[i] = ImageIO.read(classLoader.getResourceAsStream(String.format("images/bullet/emoji%d.png", i + 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
