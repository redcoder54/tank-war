package redcoder.tank;

import redcoder.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageResource {

    public static BufferedImage goodTank1L, goodTank1R, goodTank1U, goodTank1D;
    public static BufferedImage goodTank2L, goodTank2R, goodTank2U, goodTank2D;
    public static BufferedImage badTank1L, badTank1R, badTank1U, badTank1D;
    public static BufferedImage badTank2L, badTank2R, badTank2U, badTank2D;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage missileL, missileR, missileU, missileD;
    public static BufferedImage nuclearBombL, nuclearBombU, nuclearBombR, nuclearBombD;
    public static BufferedImage[] booms = new BufferedImage[16];
    public static BufferedImage[] squares = new BufferedImage[6];
    public static BufferedImage[] emoji = new BufferedImage[5];
    public static BufferedImage wall;

    static {
        try {
            goodTank1U = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTank1L = ImageUtils.rotateImage(goodTank1U, -90);
            goodTank1R = ImageUtils.rotateImage(goodTank1U, 90);
            goodTank1D = ImageUtils.rotateImage(goodTank1U, 180);

            goodTank2U = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            goodTank2L = ImageUtils.rotateImage(goodTank2U, -90);
            goodTank2R = ImageUtils.rotateImage(goodTank2U, 90);
            goodTank2D = ImageUtils.rotateImage(goodTank2U, 180);

            badTank1U = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTank1L = ImageUtils.rotateImage(badTank1U, -90);
            badTank1R = ImageUtils.rotateImage(badTank1U, 90);
            badTank1D = ImageUtils.rotateImage(badTank1U, 180);

            badTank2U = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTank2L = ImageUtils.rotateImage(badTank2U, -90);
            badTank2R = ImageUtils.rotateImage(badTank2U, 90);
            badTank2D = ImageUtils.rotateImage(badTank2U, 180);

            bulletU = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtils.rotateImage(bulletU, -90);
            bulletR = ImageUtils.rotateImage(bulletU, 90);
            bulletD = ImageUtils.rotateImage(bulletU, 180);

            BufferedImage image = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/missileLU.gif"));
            missileU = ImageUtils.rotateImage(image, 45);
            missileL = ImageUtils.rotateImage(missileU, -90);
            missileR = ImageUtils.rotateImage(missileU, 90);
            missileD = ImageUtils.rotateImage(missileU, 180);

            for (int i = 0; i < booms.length; i++) {
                booms[i] = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream(String.format("images/boom%d.gif", i + 1)));
            }

            for (int i = 0; i < squares.length; i++) {
                squares[i] = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream(String.format("images/square%d.jpg", i + 1)));
            }

            nuclearBombU = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/nuclearBomb.png"));
            nuclearBombL = ImageUtils.rotateImage(nuclearBombU, -90);
            nuclearBombR = ImageUtils.rotateImage(nuclearBombU, 90);
            nuclearBombD = ImageUtils.rotateImage(nuclearBombU, 180);

            for (int i = 0; i < emoji.length; i++) {
                emoji[i] = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream(String.format("images/emoji%d.png", i + 1)));
            }

            wall = ImageIO.read(ImageResource.class.getClassLoader().getResourceAsStream("images/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
