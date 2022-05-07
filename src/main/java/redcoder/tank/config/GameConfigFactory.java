package redcoder.tank.config;

import redcoder.tank.collider.Collider;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.stage.generator.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.io.InputStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class GameConfigFactory {

    private static final String CONFIG_LOCATION = "tank.properties";

    // config prop name
    private static final String GAME_WINDOWS_WIDTH = "gameWindowsWidth";
    private static final String GAME_WINDOWS_HEIGHT = "gameWindowsHeight";
    private static final String INITIAL_TANK_COUNT = "initialTankCount";
    private static final String PLAYER_TANK_SPEED = "playerTankSpeed";
    private static final String ENEMY_TANK_SPEED = "enemyTankSpeed";
    private static final String PLAYER_FIRE_STRATEGY = "playerFireStrategy";
    private static final String ENEMY_FIRE_STRATEGY = "enemyFireStrategy";
    private static final String TANK_PRODUCER = "tankProducer";
    private static final String CUSTOM_COLLIDER = "customCollider";
    private static final String CUSTOM_STAGE_GENERATOR = "customStageGenerator";

    // config prop default value
    private static final String DEFAULT_GAME_WINDOWS_WIDTH = "900";
    private static final String DEFAULT_GAME_WINDOWS_HEIGHT = "600";
    private static final String DEFAULT_INITIAL_TANK_COUNT = "9";
    private static final String DEFAULT_PLAYER_TANK_SPEED = "20";
    private static final String DEFAULT_ENEMY_TANK_SPEED = "20";
    private static final String DEFAULT_PLAYER_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    private static final String DEFAULT_ENEMY_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    private static final String DEFAULT_TANK_PRODUCER = "redcoder.tank.tankproducer.DefaultTankProducer";

    private static final GameConfig GAME_CONFIG = new GameConfig();
    static{
        initGameConfig();
    }

    private GameConfigFactory() {
    }

    public static GameConfig getGameConfig() {
        return GAME_CONFIG;
    }

    @SuppressWarnings("unchecked")
    private static void initGameConfig(){
        try {
            Map<String, String> configProps = getConfigProps();
            
            // 游戏窗口宽度
            String value = configProps.getOrDefault(GAME_WINDOWS_WIDTH, DEFAULT_GAME_WINDOWS_WIDTH);
            GAME_CONFIG.setGameWindowsWidth(parseInt(value));

            // 游戏窗口高度
            value = configProps.getOrDefault(GAME_WINDOWS_HEIGHT, DEFAULT_GAME_WINDOWS_HEIGHT);
            GAME_CONFIG.setGameWindowsHeight(parseInt(value));

            // 坦克（敌方）数量
            value = configProps.getOrDefault(INITIAL_TANK_COUNT, DEFAULT_INITIAL_TANK_COUNT);
            GAME_CONFIG.setInitialTankCount(parseInt(value));

            // 玩家坦克速度
            value = configProps.getOrDefault(PLAYER_TANK_SPEED, DEFAULT_PLAYER_TANK_SPEED);
            GAME_CONFIG.setPlayerTankSpeed(parseInt(value));

            // 敌方坦克速度
            value = configProps.getOrDefault(ENEMY_TANK_SPEED, DEFAULT_ENEMY_TANK_SPEED);
            GAME_CONFIG.setEnemyTankSpeed(parseInt(value));

            // 玩家坦克开火策略
            value = configProps.getOrDefault(PLAYER_FIRE_STRATEGY, DEFAULT_PLAYER_FIRE_STRATEGY);
            Class<FireStrategy> fireSyClazz = (Class<FireStrategy>) Class.forName(value.trim());
            GAME_CONFIG.setPlayerFireStrategy(fireSyClazz.newInstance());

            // 敌方坦克开火策略
            value = configProps.getOrDefault(ENEMY_FIRE_STRATEGY, DEFAULT_ENEMY_FIRE_STRATEGY);
            fireSyClazz = (Class<FireStrategy>) Class.forName(value.trim());
            GAME_CONFIG.setEnemyFireStrategy(fireSyClazz.newInstance());

            // 游戏物体碰撞器
            value = configProps.getOrDefault(CUSTOM_COLLIDER, "");
            if (!value.trim().isEmpty()) {
                List<Collider> customColliders = new ArrayList<>();
                for (String clazz : value.split(",")) {
                    Class<Collider> colliderClazz = (Class<Collider>) Class.forName(clazz.trim());
                    customColliders.add(colliderClazz.newInstance());
                }
                GAME_CONFIG.setCustomColliders(customColliders);
            }

            // 坦克生产者
            value = configProps.getOrDefault(TANK_PRODUCER, DEFAULT_TANK_PRODUCER);
            Class<TankProducer> tankProducerClz = (Class<TankProducer>) Class.forName(value.trim());
            GAME_CONFIG.setTankProducer(tankProducerClz.newInstance());

            // 自定义的关卡生成器
            value = configProps.getOrDefault(CUSTOM_STAGE_GENERATOR, "");
            if (!value.trim().isEmpty()) {
                List<StageGenerator> customStageGenerators = new ArrayList<>();
                for (String clazz : value.split(",")) {
                    Class<StageGenerator> stageGeneratorClazz = (Class<StageGenerator>) Class.forName(clazz.trim());
                    customStageGenerators.add(stageGeneratorClazz.newInstance());
                }
                GAME_CONFIG.setCustomStageGenerators(customStageGenerators);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("创建游戏配置失败");
        }
    }
    

    private static Map<String, String> getConfigProps() {
        try {
            ClassLoader classLoader = GameConfigFactory.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(CONFIG_LOCATION);
            Properties properties = new Properties();
            properties.load(inputStream);

            Map<String, String> configProps = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                configProps.put((String) entry.getKey(), (String) entry.getValue());
            }
            return configProps;
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件失败");
        }
    }
}
