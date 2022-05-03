package redcoder.tank.config;

import redcoder.tank.collider.Collider;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.stage.StageGenerator;
import redcoder.tank.tankegenaretor.TankProducer;

import java.util.List;

/**
 * 游戏配置
 */
public class GameConfig {

    private int gameWindowsWidth;
    private int gameWindowsHeight;
    private int initialTankCount;
    private int playerTankSpeed;
    private int enemyTankSpeed;
    private FireStrategy playerFireStrategy;
    private FireStrategy enemyFireStrategy;
    private List<Collider> customColliders;
    private TankProducer tankProducer;
    private List<StageGenerator> customStateGenerators;

    private GameConfig() {
    }

    public int getGameWindowsWidth() {
        return gameWindowsWidth;
    }

    public int getGameWindowsHeight() {
        return gameWindowsHeight;
    }

    public int getInitialTankCount() {
        return initialTankCount;
    }

    public int getPlayerTankSpeed() {
        return playerTankSpeed;
    }

    public int getEnemyTankSpeed() {
        return enemyTankSpeed;
    }

    public FireStrategy getPlayerFireStrategy() {
        return playerFireStrategy;
    }

    public FireStrategy getEnemyFireStrategy() {
        return enemyFireStrategy;
    }

    public List<Collider> getCustomColliders() {
        return customColliders;
    }

    public TankProducer getTankProducer() {
        return tankProducer;
    }

    public List<StageGenerator> getCustomStateGenerators() {
        return customStateGenerators;
    }

    public static class GameConfigBuilder{
        private int gameWindowsWidth;
        private int gameWindowsHeight;
        private int initialTankCount;
        private int playerTankSpeed;
        private int enemyTankSpeed;
        private FireStrategy playerFireStrategy;
        private FireStrategy enemyFireStrategy;
        private List<Collider> customColliders;
        private TankProducer tankProducer;
        private List<StageGenerator> customStateGenerators;

        public GameConfigBuilder setGameWindowsWidth(int gameWindowsWidth) {
            this.gameWindowsWidth = gameWindowsWidth;
            return this;
        }

        public GameConfigBuilder setGameWindowsHeight(int gameWindowsHeight) {
            this.gameWindowsHeight = gameWindowsHeight;
            return this;
        }

        public GameConfigBuilder setInitialTankCount(int initialTankCount) {
            this.initialTankCount = initialTankCount;
            return this;
        }

        public GameConfigBuilder setPlayerTankSpeed(int playerTankSpeed) {
            this.playerTankSpeed = playerTankSpeed;
            return this;
        }

        public GameConfigBuilder setEnemyTankSpeed(int enemyTankSpeed) {
            this.enemyTankSpeed = enemyTankSpeed;
            return this;
        }

        public GameConfigBuilder setPlayerFireStrategy(FireStrategy playerFireStrategy) {
            this.playerFireStrategy = playerFireStrategy;
            return this;
        }

        public GameConfigBuilder setEnemyFireStrategy(FireStrategy enemyFireStrategy) {
            this.enemyFireStrategy = enemyFireStrategy;
            return this;
        }

        public GameConfigBuilder setCustomColliders(List<Collider> customColliders) {
            this.customColliders = customColliders;
            return this;
        }

        public GameConfigBuilder setTankProducer(TankProducer tankProducer) {
            this.tankProducer = tankProducer;
            return this;
        }

        public GameConfigBuilder setCustomStateGenerators(List<StageGenerator> customStateGenerators) {
            this.customStateGenerators = customStateGenerators;
            return this;
        }

        public GameConfig build() {
            GameConfig gameConfig = new GameConfig();

            gameConfig.initialTankCount = this.initialTankCount;
            gameConfig.gameWindowsWidth = this.gameWindowsWidth;
            gameConfig.gameWindowsHeight = this.gameWindowsHeight;
            gameConfig.playerTankSpeed = this.playerTankSpeed;
            gameConfig.enemyTankSpeed = this.enemyTankSpeed;
            gameConfig.playerFireStrategy = this.playerFireStrategy;
            gameConfig.enemyFireStrategy = this.enemyFireStrategy;
            gameConfig.customColliders = this.customColliders;
            gameConfig.tankProducer = this.tankProducer;
            gameConfig.customStateGenerators = this.customStateGenerators;

            return gameConfig;
        }
    }

}
