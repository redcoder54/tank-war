package redcoder.tank;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest {

    @Test
    public void test() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(bufferedImage);
    }
}
