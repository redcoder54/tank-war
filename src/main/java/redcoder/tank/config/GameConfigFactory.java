package redcoder.tank.config;

import redcoder.tank.config.parser.ConfigFileParser;
import redcoder.tank.config.parser.PropConfigFileParser;

public class GameConfigFactory {

    private static GameConfig gameConfig;

    static {
        initGameConfig();
    }

    private GameConfigFactory() {

    }

    public static GameConfig getGameConfig() {
        return gameConfig;
    }

    private static void initGameConfig() {
        ConfigFileParser parser = new PropConfigFileParser();
        gameConfig = parser.parse("config.properties");
    }


}
