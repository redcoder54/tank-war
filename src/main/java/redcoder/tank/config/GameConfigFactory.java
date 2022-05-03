package redcoder.tank.config;

import redcoder.tank.config.parser.ConfigFileParser;
import redcoder.tank.config.parser.PropConfigFileParser;

public class GameConfigFactory {

    private static final GameConfigFactory INSTANCE = new GameConfigFactory();

    private GameConfig gameConfig;

    private GameConfigFactory() {
        initGameConfig();
    }

    public static GameConfigFactory getInstance() {
        return INSTANCE;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    private void initGameConfig() {
        ConfigFileParser parser = new PropConfigFileParser();
        gameConfig = parser.parse("config.properties");
    }


}
