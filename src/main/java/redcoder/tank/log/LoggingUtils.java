package redcoder.tank.log;

import redcoder.tank.utils.SystemUtils;

import java.io.File;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingUtils {

    public static void resetLogManager() {
        try (InputStream in = LoggingUtils.class.getClassLoader().getResourceAsStream("log.properties")) {
            ensureLogDirExist();
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ensureLogDirExist() {
        String home = SystemUtils.getUserHome();
        File dir = new File(home, "tank-war/log");
        if (dir.exists()) {
            return;
        }
        if (!dir.mkdir()) {
            throw new RuntimeException("Failed to create log dir!");
        }
    }
}
