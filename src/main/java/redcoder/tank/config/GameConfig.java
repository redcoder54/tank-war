package redcoder.tank.config;

import redcoder.tank.collider.ColliderChain;
import redcoder.tank.fire.FireStrategy;
import redcoder.tank.tankegenaretor.TankProducer;

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
    private ColliderChain colliderChain;
    private TankProducer tankProducer;

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

    public ColliderChain getColliderChain() {
        return colliderChain;
    }

    public TankProducer getTankProducer() {
        return tankProducer;
    }

    public static class GameConfigBuilder{
        private int gameWindowsWidth;
        private int gameWindowsHeight;
        private int initialTankCount;
        private int playerTankSpeed;
        private int enemyTankSpeed;
        private FireStrategy playerFireStrategy;
        private FireStrategy enemyFireStrategy;
        private ColliderChain colliderChain;
        private TankProducer tankProducer;

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

        public GameConfigBuilder setColliderChain(ColliderChain colliderChain) {
            this.colliderChain = colliderChain;
            return this;
        }

        public GameConfigBuilder setTankProducer(TankProducer tankProducer) {
            this.tankProducer = tankProducer;
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
            gameConfig.colliderChain = this.colliderChain;
            gameConfig.tankProducer = this.tankProducer;

            return gameConfig;
        }
    }

}
