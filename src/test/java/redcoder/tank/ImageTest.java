package redcoder.tank;

import org.junit.Assert;
import org.junit.Test;
import redcoder.tank.log.LoggingUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageTest {

    private static final Logger LOGGER = Logger.getLogger("redcoder.tank.ImageTest");

    @Test
    public void test() {
        LoggingUtils.resetLogManager();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/boom/0.gif"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "", e);
        }
        Assert.assertNotNull(bufferedImage);
    }
}
