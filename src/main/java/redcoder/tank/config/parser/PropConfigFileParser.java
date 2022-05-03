package redcoder.tank.config.parser;

import redcoder.tank.collider.Collider;
import redcoder.tank.collider.ColliderChain;
import redcoder.tank.config.GameConfig;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.tankegenaretor.TankProducer;

import java.util.Properties;

import static java.lang.Integer.parseInt;

/**
 * properties格式的配置文件解析器
 */
public class PropConfigFileParser implements ConfigFileParser {

    @Override
    public GameConfig parse(String configFile) throws ConfigFileParserException {
        try {
            Properties properties = new Properties();
            properties.load(PropConfigFileParser.class.getClassLoader().getResourceAsStream(configFile));
            return build(properties);
        } catch (Exception e) {
            throw new ConfigFileParserException("properties格式的配置文件解析失败", e);
        }
    }

    @SuppressWarnings("unchecked")
    private GameConfig build(Properties properties) throws Exception {
        GameConfig.GameConfigBuilder builder = new GameConfig.GameConfigBuilder();

        // 游戏窗口宽度
        builder.setGameWindowsWidth(parseInt(properties.getProperty(ConfigPropName.GAME_WINDOWS_WIDTH, ConfigDefaultValue.GAME_WINDOWS_WIDTH)));
        // 游戏窗口高度
        builder.setGameWindowsHeight(parseInt(properties.getProperty(ConfigPropName.GAME_WINDOWS_HEIGHT, ConfigDefaultValue.GAME_WINDOWS_HEIGHT)));
        // 坦克（敌方）数量
        builder.setInitialTankCount(parseInt(properties.getProperty(ConfigPropName.INITIAL_TANK_COUNT, ConfigDefaultValue.INITIAL_TANK_COUNT)));
        // 玩家坦克速度
        builder.setPlayerTankSpeed(parseInt(properties.getProperty(ConfigPropName.PLAYER_TANK_SPEED, ConfigDefaultValue.PLAYER_TANK_SPEED)));
        // 敌方坦克速度
        builder.setEnemyTankSpeed(parseInt(properties.getProperty(ConfigPropName.ENEMY_TANK_SPEED, ConfigDefaultValue.ENEMY_TANK_SPEED)));
        // 玩家坦克开火策略
        Class<FireStrategy> fireSyClazz = (Class<FireStrategy>)
                Class.forName(properties.getProperty(ConfigPropName.PLAYER_FIRE_STRATEGY, ConfigDefaultValue.PLAYER_FIRE_STRATEGY).trim());
        builder.setPlayerFireStrategy(fireSyClazz.newInstance());
        // 敌方坦克开火策略
        fireSyClazz = (Class<FireStrategy>)
                Class.forName(properties.getProperty(ConfigPropName.ENEMY_FIRE_STRATEGY, ConfigDefaultValue.ENEMY_FIRE_STRATEGY).trim());
        builder.setEnemyFireStrategy(fireSyClazz.newInstance());
        // 游戏物体碰撞器
        ColliderChain colliderChain = new ColliderChain();
        for (String clz : properties.getProperty(ConfigPropName.COLLIDER_CHAIN, ConfigDefaultValue.COLLIDER_CHAIN).split(",")) {
            Class<Collider> colliderClass = (Class<Collider>) Class.forName(clz.trim());
            colliderChain.addCollider(colliderClass.newInstance());
        }
        builder.setColliderChain(colliderChain);
        // 坦克生产者
        Class<TankProducer> tankProducerClass = (Class<TankProducer>)
                Class.forName(properties.getProperty(ConfigPropName.TANK_PRODUCER, ConfigDefaultValue.TANK_PRODUCER).trim());
        builder.setTankProducer(tankProducerClass.newInstance());

        return builder.build();
    }
}
