package redcoder.tank;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String PROP_INITIAL_TANK_COUNT = "initialTankCount";
    private static final String PROP_GAME_WINDOWS_WIDTH = "gameWindowsWidth";
    private static final String PROP_GAME_WINDOWS_HEIGHT = "gameWindowsHeight";
    private static final String PROP_PLAYER_TANK_SPEED = "playerTankSpeed";
    private static final String PROP_AI_TANK_SPEED = "AITankSpeed";
    private static final String PROP_FIRE_STRATEGY = "fireStrategy";

    private final Properties properties = new Properties();

    private ConfigManager() {
        try {
            properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ConfigManager getInstance() {
        return Holder.configManager;
    }

    public int getInitialTankCount() {
        String value = properties.getProperty(PROP_INITIAL_TANK_COUNT, "9");
        return Integer.parseInt(value);
    }

    public int getGameWindowsWidth() {
        String value = properties.getProperty(PROP_GAME_WINDOWS_WIDTH, "900");
        return Integer.parseInt(value);
    }

    public int getGameWindowsHeight() {
        String value = properties.getProperty(PROP_GAME_WINDOWS_HEIGHT, "600");
        return Integer.parseInt(value);
    }

    public int getPlayerTankSpeed() {
        String value = properties.getProperty(PROP_PLAYER_TANK_SPEED, "20");
        return Integer.parseInt(value);
    }

    public int getAITankSpeed() {
        String value = properties.getProperty(PROP_AI_TANK_SPEED, "10");
        return Integer.parseInt(value);
    }

    public String getFireStrategy() {
        return properties.getProperty(PROP_FIRE_STRATEGY, "bullet");
    }

    private static class Holder {
        static final ConfigManager configManager = new ConfigManager();
    }
}
