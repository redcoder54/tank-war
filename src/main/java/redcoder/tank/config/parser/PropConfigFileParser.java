package redcoder.tank.config.parser;

import redcoder.tank.collider.Collider;
import redcoder.tank.config.GameConfig;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.stage.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.util.ArrayList;
import java.util.List;
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
        List<Collider> customColliders = new ArrayList<>();
        String customColliderStr = properties.getProperty(ConfigPropName.CUSTOM_COLLIDER, "");
        if (!customColliderStr.isEmpty()) {
            for (String clz : customColliderStr.split(",")) {
                Class<Collider> colliderClz = (Class<Collider>) Class.forName(clz.trim());
                customColliders.add(colliderClz.newInstance());
            }
        }
        builder.setCustomColliders(customColliders);
        // 坦克生产者
        Class<TankProducer> tankProducerClz = (Class<TankProducer>)
                Class.forName(properties.getProperty(ConfigPropName.TANK_PRODUCER, ConfigDefaultValue.TANK_PRODUCER).trim());
        builder.setTankProducer(tankProducerClz.newInstance());
        // 自定义的关卡生成器
        List<StageGenerator> customStageGenerators = new ArrayList<>();
        String customStageGeneratorStr = properties.getProperty(ConfigPropName.CUSTOM_STAGE_GENERATOR, "");
        if (!customStageGeneratorStr.isEmpty()) {
            for (String clz : customStageGeneratorStr.split(",")) {
                Class<StageGenerator> stageGeneratorClz = (Class<StageGenerator>) Class.forName(clz.trim());
                customStageGenerators.add(stageGeneratorClz.newInstance());
            }
        }
        builder.setCustomStageGenerators(customStageGenerators);


        return builder.build();
    }
}
