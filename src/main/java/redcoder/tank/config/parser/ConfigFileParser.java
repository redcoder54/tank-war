package redcoder.tank.config.parser;

import redcoder.tank.config.GameConfig;

/**
 * 游戏配置文件解析器
 */
public interface ConfigFileParser {

    /**
     * 解析游戏配置文件
     *
     * @param configFile 配置文件
     * @return 游戏配置
     * @throws ConfigFileParserException 配置文件解析失败时
     */
    GameConfig parse(String configFile) throws ConfigFileParserException;

    /**
     * 配置文件解析异常
     */
    class ConfigFileParserException extends RuntimeException {
        public ConfigFileParserException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
