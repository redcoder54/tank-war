package redcoder.tank.gameobj.image.bullet;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MissileImageSupplier implements DirectionalImageSupplier {

    public static final MissileImageSupplier SUPPLIER = new MissileImageSupplier();

    @Override
    public Image getUpImage() {
        return missileU;
    }

    @Override
    public Image getLeftImage() {
        return missileL;
    }

    @Override
    public Image getRightImage() {
        return missileR;
    }

    @Override
    public Image getDownImage() {
        return missileD;
    }

    @Override
    public int getImageWidth() {
        return missileR.getWidth();
    }

    @Override
    public int getImageHeight() {
        return missileR.getHeight();
    }

    private static BufferedImage missileL, missileR, missileU, missileD;
    static {
        try {
            ClassLoader classLoader = MissileImageSupplier.class.getClassLoader();

            missileR = ImageIO.read(classLoader.getResourceAsStream("images/bullet/missileR.png"));
            missileU = ImageUtils.rotateImage(missileR, -90);
            missileD = ImageUtils.rotateImage(missileR, 90);
            missileL = ImageUtils.rotateImage(missileR, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
