package redcoder.tank;

import redcoder.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {

    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] booms = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtils.rotateImage(goodTankU, -90);
            goodTankR = ImageUtils.rotateImage(goodTankU, 90);
            goodTankD = ImageUtils.rotateImage(goodTankU, 180);

            badTankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankR = ImageUtils.rotateImage(badTankL, -90);
            badTankU = ImageUtils.rotateImage(badTankL, 90);
            badTankD = ImageUtils.rotateImage(badTankL, 180);

            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtils.rotateImage(bulletU, -90);
            bulletR = ImageUtils.rotateImage(bulletU, 90);
            bulletD = ImageUtils.rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                booms[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream(String.format("images/boom%d.gif", i + 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
