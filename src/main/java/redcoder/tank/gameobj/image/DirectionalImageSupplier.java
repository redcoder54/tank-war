package redcoder.tank.gameobj.image;

import java.awt.*;

/**
 * 区分方向的游戏物体图像提供者
 */
public interface DirectionalImageSupplier extends ImageSupplier {

    @Override
    default Image getImage() {
        return getUpImage();
    }

    Image getUpImage();

    Image getLeftImage();

    Image getRightImage();

    Image getDownImage();

    int getImageWidth();

    int getImageHeight();
}
