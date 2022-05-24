package redcoder.tank.gameobj.image.bullet;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneralBulletImageSupplier implements DirectionalImageSupplier {

    public final static GeneralBulletImageSupplier SUPPLIER = new GeneralBulletImageSupplier();

    @Override
    public Image getUpImage() {
        return bulletU;
    }

    @Override
    public Image getLeftImage() {
        return bulletL;
    }

    @Override
    public Image getRightImage() {
        return bulletR;
    }

    @Override
    public Image getDownImage() {
        return bulletD;
    }

    @Override
    public int getImageWidth() {
        return bulletL.getWidth();
    }

    @Override
    public int getImageHeight() {
        return bulletL.getHeight();
    }


    private static BufferedImage bulletL, bulletR, bulletU, bulletD;
    static {
        try {
            ClassLoader classLoader = GeneralBulletImageSupplier.class.getClassLoader();

            bulletU = ImageIO.read(classLoader.getResourceAsStream("images/bullet/bulletU.png"));
            bulletL = ImageUtils.rotateImage(bulletU, -90);
            bulletR = ImageUtils.rotateImage(bulletU, 90);
            bulletD = ImageUtils.rotateImage(bulletU, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
