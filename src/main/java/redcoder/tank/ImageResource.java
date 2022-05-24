package redcoder.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ImageResource {

    public static BufferedImage[] booms = new BufferedImage[16];
    public static BufferedImage wall;

    private static final Map<String, BufferedImage> IMAGES = new HashMap<>();

    /**
     * 获取指定名称的图标资源
     *
     * @param imageName 图标名称
     * @return 图标资源
     */
    public static BufferedImage getImage(String imageName) {
        BufferedImage image = IMAGES.get(imageName);
        if (image == null) {
            URL url = ImageResource.class.getResource(imageName);
            if (url == null) {
                url = ImageResource.class.getClassLoader().getResource(imageName);
            }
            if (url != null) {
                try {
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                IMAGES.put(imageName, image);
            }
        }
        return image;
    }

    static {
        loadInternalImage();
    }

    private static void loadInternalImage() {
        try {
            loadImage();
            loadBulletImage();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ImageResource.loadInternalImage", e);
        }
    }

    private static void loadImage() throws IOException {
        for (int i = 0; i < booms.length; i++) {
            booms[i] = ImageIO.read(getStream(String.format("images/boom/boom%d.gif", i + 1)));
        }

        wall = ImageIO.read(getStream("images/obstacle/wall.png"));
    }

    private static InputStream getStream(String name) {
        return ImageResource.class.getClassLoader().getResourceAsStream(name);
    }

    private static void loadBulletImage() throws Exception {
        ClassLoader classLoader = ImageResource.class.getClassLoader();
        URL url = classLoader.getResource("images/bullet");
        if (url == null) {
            return;
        }
        String str = url.toString();
        if (str.startsWith("file")) {
            loadLocalFile(url);
        } else if (str.startsWith("jar")) {
            loadJarFile(classLoader, url);
        } else {
            LOGGER.log(Level.WARNING, "Unknown URL[ " + str + "], we can't handle it.");
        }
    }

    private static void loadLocalFile(URL url) throws Exception {
        File file = new File(url.toURI());
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    String iconName = f.getName();
                    String path = f.getAbsolutePath();
                    IMAGES.put(iconName, ImageIO.read(new File(path)));
                }
            }
        }
    }

    private static void loadJarFile(ClassLoader classLoader, URL url) throws Exception {
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        JarFile jarFile = connection.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            String name = entries.nextElement().getName();
            if (name.endsWith(".gif") || name.endsWith(".png") || name.endsWith(".jpg")) {
                URL resource = classLoader.getResource(name);
                if (resource != null) {
                    String iconName = name.substring(name.lastIndexOf("/") + 1);
                    IMAGES.put(iconName, ImageIO.read(resource));
                }
            }
        }
    }
}
