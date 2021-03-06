package redcoder.tank.model;

import redcoder.tank.TankPanel;
import redcoder.tank.collider.*;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.Direction;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Group;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.image.tank.PlayerTankImageSupplier;
import redcoder.tank.stage.DefaultGameStageSwitchController;
import redcoder.tank.stage.GameStageSwitchController;
import redcoder.tank.stage.deployer.CyclicStageDeployer;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.stage.generator.Stage1Generator;
import redcoder.tank.stage.generator.Stage2Generator;
import redcoder.tank.stage.generator.StageGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class GameModelCreator {

    private final static ReentrantLock LOCK = new ReentrantLock();

    public static GameModel create() {
        if (LOCK.tryLock()) {
            try {
                GameModel gameModel = new GameModel();
                initGameModel(gameModel);
                return gameModel;
            } finally {
                LOCK.unlock();
            }
        }
        throw new IllegalStateException("Another thread is creating GameModel");
    }

    private static void initGameModel(GameModel gameModel) {
        GameConfig gameConfig = GameConfigFactory.getGameConfig();

        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        Tank playerTank = new Tank(TankPanel.WIDTH / 2, TankPanel.HEIGHT - 60, playerTankSpeed, Direction.UP,
                Group.GOOD, false, PlayerTankImageSupplier.SUPPLIER);
        gameModel.setPlayerTank(playerTank);

        List<GameObj> gameObjs = new CopyOnWriteArrayList<>();
        gameObjs.add(playerTank);
        gameModel.setGameObjs(gameObjs);

        ColliderChain colliderChain = new ColliderChain("defaultColliderChain");
        configureCollider(colliderChain, gameConfig);
        gameModel.setColliderChain(colliderChain);

        GameProgress gameProgress = new GameProgress();
        gameModel.setGameProgress(gameProgress);

        StageDeployer stageDeployer = new CyclicStageDeployer();
        configureStageGenerator(stageDeployer, gameConfig);
        gameModel.setStageDeployer(stageDeployer);

        GameStageSwitchController gameStageSwitchController = new DefaultGameStageSwitchController(stageDeployer);
        gameModel.setGameStageSwitchController(gameStageSwitchController);
    }

    private static void configureCollider(ColliderChain colliderChain, GameConfig gameConfig) {
        // ????????????????????????
        addDefaultCollider(colliderChain);

        // ???????????????????????????
        List<Collider> customColliders = gameConfig.getCustomColliders();
        if (customColliders != null && !customColliders.isEmpty()) {
            customColliders.forEach(colliderChain::addCollider);
        }
    }

    private static void configureStageGenerator(StageDeployer stageDeployer, GameConfig gameConfig) {
        // ??????????????????????????????
        addDefaultStateGenerator(stageDeployer);

        // ?????????????????????????????????
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
}
