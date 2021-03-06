package redcoder.tank.config;

import redcoder.tank.collider.Collider;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.producer.TankProducer;
import redcoder.tank.stage.generator.StageGenerator;

import java.io.InputStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class GameConfigFactory {

    private static final String CONFIG_LOCATION = "tank.properties";

    // config prop name
    private static final String ENABLE_MUSIC = "enableMusic";
    private static final String INITIAL_TANK_COUNT = "initialTankCount";
    private static final String PLAYER_TANK_SPEED = "playerTankSpeed";
    private static final String ENEMY_TANK_SPEED = "enemyTankSpeed";
    private static final String PLAYER_FIRE_STRATEGY = "playerFireStrategy";
    private static final String ENEMY_FIRE_STRATEGY = "enemyFireStrategy";
    private static final String TANK_PRODUCER = "tankProducer";
    private static final String CUSTOM_COLLIDER = "customCollider";
    private static final String CUSTOM_STAGE_GENERATOR = "customStageGenerator";

    // config prop default value
    private static final String DEFAULT_INITIAL_TANK_COUNT = "9";
    private static final String DEFAULT_PLAYER_TANK_SPEED = "20";
    private static final String DEFAULT_ENEMY_TANK_SPEED = "20";
    private static final String DEFAULT_PLAYER_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    private static final String DEFAULT_ENEMY_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    private static final String DEFAULT_TANK_PRODUCER = "redcoder.tank.tankproducer.DefaultTankProducer";

    private static final GameConfig GAME_CONFIG = new GameConfig();

    static {
        initGameConfig();
    }

    private GameConfigFactory() {
    }

    public static GameConfig getGameConfig() {
        return GAME_CONFIG;
    }

    @SuppressWarnings("unchecked")
    private static void initGameConfig() {
        try {
            Map<String, String> configProps = getConfigProps();

            String enableMusic = configProps.getOrDefault(ENABLE_MUSIC, "true");
            GAME_CONFIG.setEnableMusic(Boolean.parseBoolean(enableMusic));

            // ????????????????????????
            String value = configProps.getOrDefault(INITIAL_TANK_COUNT, DEFAULT_INITIAL_TANK_COUNT);
            GAME_CONFIG.setInitialTankCount(parseInt(value));

            // ??????????????????
            value = configProps.getOrDefault(PLAYER_TANK_SPEED, DEFAULT_PLAYER_TANK_SPEED);
            GAME_CONFIG.setPlayerTankSpeed(parseInt(value));

            // ??????????????????
            value = configProps.getOrDefault(ENEMY_TANK_SPEED, DEFAULT_ENEMY_TANK_SPEED);
            GAME_CONFIG.setEnemyTankSpeed(parseInt(value));

            // ????????????????????????
            value = configProps.getOrDefault(PLAYER_FIRE_STRATEGY, DEFAULT_PLAYER_FIRE_STRATEGY);
            Class<FireStrategy> fireSyClazz = (Class<FireStrategy>) Class.forName(value.trim());
            GAME_CONFIG.setPlayerFireStrategy(fireSyClazz.newInstance());

            // ????????????????????????
            value = configProps.getOrDefault(ENEMY_FIRE_STRATEGY, DEFAULT_ENEMY_FIRE_STRATEGY);
            fireSyClazz = (Class<FireStrategy>) Class.forName(value.trim());
            GAME_CONFIG.setEnemyFireStrategy(fireSyClazz.newInstance());

            // ?????????????????????
            value = configProps.getOrDefault(CUSTOM_COLLIDER, "");
            if (!value.trim().isEmpty()) {
                List<Collider> customColliders = new ArrayList<>();
                for (String clazz : value.split(",")) {
                    Class<Collider> colliderClazz = (Class<Collider>) Class.forName(clazz.trim());
                    customColliders.add(colliderClazz.newInstance());
                }
                GAME_CONFIG.setCustomColliders(customColliders);
            }

            // ???????????????
            value = configProps.getOrDefault(TANK_PRODUCER, DEFAULT_TANK_PRODUCER);
            Class<TankProducer> tankProducerClz = (Class<TankProducer>) Class.forName(value.trim());
            GAME_CONFIG.setTankProducerClass(tankProducerClz);

            // ???????????????????????????
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
            throw new RuntimeException("????????????????????????");
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
            throw new RuntimeException("????????????????????????");
        }
    }
}
