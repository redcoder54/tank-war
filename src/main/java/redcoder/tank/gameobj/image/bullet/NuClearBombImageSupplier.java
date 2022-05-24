package redcoder.tank.gameobj.image.bullet;

import redcoder.tank.gameobj.image.DirectionalImageSupplier;
import redcoder.tank.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class NuClearBombImageSupplier implements DirectionalImageSupplier {

    public static final NuClearBombImageSupplier SUPPLIER = new NuClearBombImageSupplier();

    @Override
    public Image getUpImage() {
        return nuclearBombU;
    }

    @Override
    public Image getLeftImage() {
        return nuclearBombL;
    }

    @Override
    public Image getRightImage() {
        return nuclearBombR;
    }

    @Override
    public Image getDownImage() {
        return nuclearBombD;
    }

    @Override
    public int getImageWidth() {
        return nuclearBombL.getWidth();
    }

    @Override
    public int getImageHeight() {
        return nuclearBombL.getHeight();
    }

    private static BufferedImage nuclearBombL,nuclearBombR,nuclearBombU,nuclearBombD;
    static {
        try {
            ClassLoader classLoader = NuClearBombImageSupplier.class.getClassLoader();
            nuclearBombD = ImageIO.read(classLoader.getResourceAsStream("images/bullet/nuclearBomb.png"));
            nuclearBombL = ImageUtils.rotateImage(nuclearBombD, -90);
            nuclearBombR = ImageUtils.rotateImage(nuclearBombD, 90);
            nuclearBombU = ImageUtils.rotateImage(nuclearBombD, 180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
