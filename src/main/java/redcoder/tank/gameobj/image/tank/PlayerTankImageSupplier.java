package redcoder.tank.gameobj.image.tank;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PlayerTankImageSupplier implements DirectionalImageSupplier {

    public static final PlayerTankImageSupplier SUPPLIER = new PlayerTankImageSupplier();

    private final Random random = new Random();

    @Override
    public Image getUpImage() {
        return random.nextInt(10) > 5 ? goodTank1U : goodTank2U;
    }

    @Override
    public Image getLeftImage() {
        return random.nextInt(10) > 5 ? goodTank1L : goodTank2L;
    }

    @Override
    public Image getRightImage() {
        return random.nextInt(10) > 5 ? goodTank1R : goodTank2R;
    }

    @Override
    public Image getDownImage() {
        return random.nextInt(10) > 5 ? goodTank1D : goodTank2D;
    }

    @Override
    public int getImageWidth() {
        return goodTank1L.getWidth();
    }

    @Override
    public int getImageHeight() {
        return goodTank1L.getHeight();
    }

    private static BufferedImage goodTank1L, goodTank1R, goodTank1U, goodTank1D;
    private static BufferedImage goodTank2L, goodTank2R, goodTank2U, goodTank2D;
    static {
        try {
            ClassLoader classLoader = PlayerTankImageSupplier.class.getClassLoader();

            goodTank1U = ImageIO.read(classLoader.getResourceAsStream("images/tank/GoodTank1.png"));
            goodTank1L = ImageUtils.rotateImage(goodTank1U, -90);
            goodTank1R = ImageUtils.rotateImage(goodTank1U, 90);
            goodTank1D = ImageUtils.rotateImage(goodTank1U, 180);

            goodTank2U = ImageIO.read(classLoader.getResourceAsStream("images/tank/GoodTank2.png"));
            goodTank2L = ImageUtils.rotateImage(goodTank2U, -90);
            goodTank2R = ImageUtils.rotateImage(goodTank2U, 90);
            goodTank2D = ImageUtils.rotateImage(goodTank2U, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
