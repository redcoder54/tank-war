package redcoder.tank.gameobj.image.tank;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyTankImageSupplier implements DirectionalImageSupplier {

    public static final EnemyTankImageSupplier SUPPLIER = new EnemyTankImageSupplier();
    
    private final Random random = new Random();

    @Override
    public Image getUpImage() {
        return random.nextInt(10) > 5 ? badTank1U : badTank2U;
    }

    @Override
    public Image getLeftImage() {
        return random.nextInt(10) > 5 ? badTank1L : badTank2L;
    }

    @Override
    public Image getRightImage() {
        return random.nextInt(10) > 5 ? badTank1R : badTank2R;
    }

    @Override
    public Image getDownImage() {
        return random.nextInt(10) > 5 ? badTank1D : badTank2D;
    }

    @Override
    public int getImageWidth() {
        return badTank1L.getWidth();
    }

    @Override
    public int getImageHeight() {
        return badTank1L.getHeight();
    }

    private static BufferedImage badTank1L, badTank1R, badTank1U, badTank1D;
    private static BufferedImage badTank2L, badTank2R, badTank2U, badTank2D;
    static {
        try {
            ClassLoader classLoader = EnemyTankImageSupplier.class.getClassLoader();

            badTank1U = ImageIO.read(classLoader.getResourceAsStream("images/tank/BadTank1.png"));
            badTank1L = ImageUtils.rotateImage(badTank1U, -90);
            badTank1R = ImageUtils.rotateImage(badTank1U, 90);
            badTank1D = ImageUtils.rotateImage(badTank1U, 180);

            badTank2U = ImageIO.read(classLoader.getResourceAsStream("images/tank/BadTank2.png"));
            badTank2L = ImageUtils.rotateImage(badTank2U, -90);
            badTank2R = ImageUtils.rotateImage(badTank2U, 90);
            badTank2D = ImageUtils.rotateImage(badTank2U, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
