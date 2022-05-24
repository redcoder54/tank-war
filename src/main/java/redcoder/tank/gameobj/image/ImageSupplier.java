package redcoder.tank.gameobj.image;

import java.awt.*;
import java.io.Serializable;

/**
 * 不区分方向的游戏物体图像提供者
 */
public interface ImageSupplier extends Serializable {

    Image getImage();

    int getImageWidth();

    int getImageHeight();
}
