package redcoder.tank;

import redcoder.tank.collider.Collider;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.stage.generator.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.util.List;

/**
 * 游戏配置
 */
public class GameConfig {

    private static final GameConfig GAME_CONFIG = GameConfigFactory.getGameConfig();

    public static GameConfig getGameConfig() {
        return GAME_CONFIG;
    }

    private int initialTankCount;
    private int playerTankSpeed;
    private int enemyTankSpeed;
    private FireStrategy playerFireStrategy;
    private FireStrategy enemyFireStrategy;
    private List<Collider> customColliders;
    private TankProducer tankProducer;
    private List<StageGenerator> customStageGenerators;

    public int getInitialTankCount() {
        return initialTankCount;
    }

    public void setInitialTankCount(int initialTankCount) {
        this.initialTankCount = initialTankCount;
    }

    public int getPlayerTankSpeed() {
        return playerTankSpeed;
    }

    public void setPlayerTankSpeed(int playerTankSpeed) {
        this.playerTankSpeed = playerTankSpeed;
    }

    public int getEnemyTankSpeed() {
        return enemyTankSpeed;
    }

    public void setEnemyTankSpeed(int enemyTankSpeed) {
        this.enemyTankSpeed = enemyTankSpeed;
    }

    public FireStrategy getPlayerFireStrategy() {
        return playerFireStrategy;
    }

    public void setPlayerFireStrategy(FireStrategy playerFireStrategy) {
        this.playerFireStrategy = playerFireStrategy;
    }

    public FireStrategy getEnemyFireStrategy() {
        return enemyFireStrategy;
    }

    public void setEnemyFireStrategy(FireStrategy enemyFireStrategy) {
        this.enemyFireStrategy = enemyFireStrategy;
    }

    public List<Collider> getCustomColliders() {
        return customColliders;
    }

    public void setCustomColliders(List<Collider> customColliders) {
        this.customColliders = customColliders;
    }

    public TankProducer getTankProducer() {
        return tankProducer;
    }

    public void setTankProducer(TankProducer tankProducer) {
        this.tankProducer = tankProducer;
    }

    public List<StageGenerator> getCustomStageGenerators() {
        return customStageGenerators;
    }

    public void setCustomStageGenerators(List<StageGenerator> customStageGenerators) {
        this.customStageGenerators = customStageGenerators;
    }
}
