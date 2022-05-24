package redcoder.tank;

import redcoder.tank.collider.*;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.PlayerTankImageSupplier;
import redcoder.tank.stage.DefaultGameStageSwitchController;
import redcoder.tank.stage.GameStageSwitchController;
import redcoder.tank.stage.deployer.CyclicStageDeployer;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.stage.generator.Stage1Generator;
import redcoder.tank.stage.generator.Stage2Generator;
import redcoder.tank.stage.generator.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class GameModelFactory {

    private final static ReentrantLock LOCK = new ReentrantLock();

    public static GameModel getGameModel() {
        if (LOCK.tryLock()) {
            try {
                GameModel gameModel = new GameModel();
                initGameModel(gameModel);
                GameModelWrapper.gameModel = gameModel;
                return gameModel;
            } finally {
                LOCK.unlock();
            }
        }
        return null;
    }

    private static void initGameModel(GameModel gameModel) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();

        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        Tank playerTank = new Tank(TankPanel.WIDTH / 2, TankPanel.HEIGHT - 60, playerTankSpeed, Direction.UP,
                Group.GOOD, false, PlayerTankImageSupplier.SUPPLIER);

        List<GameObj> gameObjs = new CopyOnWriteArrayList<>();
        gameObjs.add(playerTank);

        ColliderChain colliderChain = new ColliderChain("defaultColliderChain");
        configureCollider(colliderChain, gameConfig);

        TankProducer tankProducer = gameConfig.getTankProducer();

        GameProgress gameProgress = new GameProgress();

        StageDeployer stageDeployer = new CyclicStageDeployer();
        configureStageGenerator(stageDeployer, gameConfig);
        GameStageSwitchController gameStageSwitchController = new DefaultGameStageSwitchController(stageDeployer);
        // 启动游戏关卡切换控制器
        gameStageSwitchController.start(gameModel);

        gameModel.setPlayerTank(playerTank);
        gameModel.setGameObjs(gameObjs);
        gameModel.setColliderChain(colliderChain);
        gameModel.setTankProducer(tankProducer);
        gameModel.setGameProgress(gameProgress);
        gameModel.setStageDeployer(stageDeployer);
        gameModel.setGameStageSwitchController(gameStageSwitchController);
    }

    private static void configureCollider(ColliderChain colliderChain, GameConfig gameConfig) {
        // 添加默认的碰撞器
        addDefaultCollider(colliderChain);

        // 添加自定义的碰撞器
        List<Collider> customColliders = gameConfig.getCustomColliders();
        if (customColliders != null && !customColliders.isEmpty()) {
            customColliders.forEach(colliderChain::addCollider);
        }
    }

    private static void configureStageGenerator(StageDeployer stageDeployer, GameConfig gameConfig) {
        // 添加默认的关卡生成器
        addDefaultStateGenerator(stageDeployer);

        // 添加自定义的关卡生成器
        List<StageGenerator> customStageGenerators = gameConfig.getCustomStageGenerators();
        if (customStageGenerators != null && !customStageGenerators.isEmpty()) {
            customStageGenerators.forEach(stageDeployer::addStageGenerator);
        }
    }

    private static void addDefaultCollider(ColliderChain colliderChain) {
        colliderChain.addCollider(new BulletTankCollider());
        colliderChain.addCollider(new TankTankCollider());
        colliderChain.addCollider(new TankWallCollider());
        colliderChain.addCollider(new BulletWallCollider());
        colliderChain.addCollider(new BulletBulletCollider());
    }

    private static void addDefaultStateGenerator(StageDeployer stageDeployer) {
        stageDeployer.addStageGenerator(new Stage1Generator());
        stageDeployer.addStageGenerator(new Stage2Generator());
    }

    public static class GameModelWrapper {

        private static GameModel gameModel = new GameModel();

        private GameModelWrapper() {
        }

        public static GameModel getGameModel() {
            return gameModel;
        }
    }
}
