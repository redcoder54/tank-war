package redcoder.tank.gameobj.image.bullet;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BombImageSupplier implements DirectionalImageSupplier {

    public static final BombImageSupplier SUPPLIER = new BombImageSupplier();

    @Override
    public Image getUpImage() {
        return bombU;
    }

    @Override
    public Image getLeftImage() {
        return bombL;
    }

    @Override
    public Image getRightImage() {
        return bombR;
    }

    @Override
    public Image getDownImage() {
        return bombD;
    }

    @Override
    public int getImageWidth() {
        return bombR.getWidth();
    }

    @Override
    public int getImageHeight() {
        return bombR.getHeight();
    }

    private static BufferedImage bombL, bombR, bombU, bombD;
    static {
        try {
            ClassLoader classLoader = BombImageSupplier.class.getClassLoader();
            bombD = ImageIO.read(classLoader.getResourceAsStream("images/bullet/bomb.png"));
            bombL = ImageUtils.rotateImage(bombD, -90);
            bombR = ImageUtils.rotateImage(bombD, 90);
            bombU = ImageUtils.rotateImage(bombD, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
