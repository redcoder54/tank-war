package redcoder.tank.audio;

import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {

    private static final Logger LOGGER = Logger.getLogger(AudioPlayer.class.getName());
    private static final GameConfig gameConfig = GameConfigFactory.getGameConfig();

    public static void playOnce(String name) {
        if (!gameConfig.isEnableMusic()) {
            return;
        }
        ScheduledUtils.schedule(() -> doPlay(name), 0, TimeUnit.SECONDS);
    }

    public static void playLoop(String name) {
        if (!gameConfig.isEnableMusic()) {
            return;
        }
        Thread thread = new Thread(() -> {
            while (true) {
                doPlay(name);
            }
        });
        thread.start();
    }

    private static void doPlay(String name) {
        try (Audio audio = new Audio(name)) {
            audio.play();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "", e);
        }
    }
}
