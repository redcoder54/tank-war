package redcoder.tank;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String PROP_INITIAL_TANK_COUNT = "initialTankCount";
    private static final String PROP_GAME_WINDOWS_WIDTH = "gameWindowsWidth";
    private static final String PROP_GAME_WINDOWS_HEIGHT = "gameWindowsHeight";
    private static final String PROP_PLAYER_TANK_SPEED = "playerTankSpeed";
    private static final String PROP_ENEMY_TANK_SPEED = "enemyTankSpeed";
    private static final String PROP_PLAYER_FIRE_STRATEGY = "playerFireStrategy";
    private static final String PROP_ENEMY_FIRE_STRATEGY = "enemyFireStrategy";
    private static final String PROP_COLLIDER_CHAIN = "colliderChain";

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

    public int getEnemyTankSpeed() {
        String value = properties.getProperty(PROP_ENEMY_TANK_SPEED, "10");
        return Integer.parseInt(value);
    }

    public String getPlayerFireStrategy() {
        return properties.getProperty(PROP_PLAYER_FIRE_STRATEGY, "redcoder.tank.fire.BulletFireStrategy");
    }

    public String getEnemyFireStrategy() {
        return properties.getProperty(PROP_ENEMY_FIRE_STRATEGY, "redcoder.tank.fire.BulletFireStrategy");
    }

    public String getColliderChain(){
        return properties.getProperty(PROP_COLLIDER_CHAIN, "redcoder.tank.collider.BulletTankCollider");
    }


    private static class Holder {
        static final ConfigManager configManager = new ConfigManager();
    }
}
