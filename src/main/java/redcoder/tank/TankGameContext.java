package redcoder.tank;

import redcoder.tank.collider.*;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.stage.*;
import redcoder.tank.stage.deployer.CyclicStageDeployer;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.stage.generator.Stage1Generator;
import redcoder.tank.stage.generator.Stage2Generator;
import redcoder.tank.stage.generator.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TankGameContext {

    private static final TankGameContext TGC = new TankGameContext();

    private int width;
    private int height;
    private Tank playerTank;
    private List<GameObj> gameObjs = new CopyOnWriteArrayList<>();
    private ColliderChain colliderChain;
    private TankProducer tankProducer;
    private StageDeployer stageDeployer;
    private GameStageSwitchController gameStageSwitchController;
    private GameProgress gameProgress;
    private GameConfig gameConfig = GameConfigFactory.getGameConfig();

    public static TankGameContext getTankGameContext() {
        return TGC;
    }

    private TankGameContext() {
        int gameWindowsWidth = gameConfig.getGameWindowsWidth();
        int gameWindowsHeight = gameConfig.getGameWindowsHeight();
        if (gameWindowsWidth < 0) {
            throw new IllegalArgumentException("Game windows width must grant than 0" +
                    ", you can set parameter 'gameWindowsWidth' to specify width.");
        }
        if (gameWindowsHeight < 0) {
            throw new IllegalArgumentException("Game windows height must grant than 0" +
                    ", you can set parameter 'gameWindowsHeight' to specify height.");
        }

        this.width = gameWindowsWidth;
        this.height = gameWindowsHeight;

        initTGC();
    }

    public void paint(Graphics g) {
        // 绘制游戏状态栏
        gameProgress.paint(g);

        for (int i = 0; i < gameObjs.size(); i++) {
            gameObjs.get(i).paint(g);
        }

        // 碰撞处理
        for (int i = 0; i < gameObjs.size(); i++) {
            for (int j = i + 1; j < gameObjs.size(); j++) {
                GameObj o1 = gameObjs.get(i);
                GameObj o2 = gameObjs.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }

    public void resetPlayerTank() {
        configurePlayerTank();
    }

    public void resetGameObj() {
        this.gameObjs.clear();
    }

    public List<GameObj> getGameObjView() {
        return new ArrayList<>(this.gameObjs);
    }

    public void addGameObj(GameObj gameObj) {
        this.gameObjs.add(gameObj);
    }

    public void removeGameObj(GameObj gameObj) {
        this.gameObjs.remove(gameObj);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public ColliderChain getColliderChain() {
        return colliderChain;
    }

    public StageDeployer getStageDeployer() {
        return stageDeployer;
    }

    public TankProducer getTankProducer() {
        return tankProducer;
    }

    public GameStageSwitchController getGameStageSwitchController() {
        return gameStageSwitchController;
    }

    public GameProgress getGameProgress() {
        return gameProgress;
    }

    // ----------- initialize TankGameContext
    private void initTGC() {
        colliderChain = new ColliderChain("defaultColliderChain");
        tankProducer = gameConfig.getTankProducer();
        stageDeployer = new CyclicStageDeployer();
        gameStageSwitchController = new DefaultGameStageSwitchController(stageDeployer);

        configurePlayerTank();
        configureCollider();
        configureStageGenerator();

        // 启动游戏关卡切换控制器
        gameProgress = gameStageSwitchController.start(this);
    }

    private void configurePlayerTank() {
        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        playerTank = new Tank(width / 2, height - Tank.HEIGHT, playerTankSpeed, Direction.UP,
                Group.GOOD, false);
        addGameObj(playerTank);
    }

    private void configureCollider() {
        // 添加默认的碰撞器
        addDefaultCollider();

        // 添加自定义的碰撞器
        List<Collider> customColliders = gameConfig.getCustomColliders();
        if (customColliders != null && !customColliders.isEmpty()) {
            customColliders.forEach(t -> colliderChain.addCollider(t));
        }
    }

    private void configureStageGenerator() {
        // 添加默认的关卡生成器
        addDefaultStateGenerator();

        // 添加自定义的关卡生成器
        List<StageGenerator> customStageGenerators = gameConfig.getCustomStageGenerators();
        if (customStageGenerators != null && !customStageGenerators.isEmpty()) {
            customStageGenerators.forEach(t -> stageDeployer.addStageGenerator(t));
        }
    }


    private void addDefaultCollider() {
        this.colliderChain.addCollider(new BulletTankCollider());
        this.colliderChain.addCollider(new TankTankCollider());
        this.colliderChain.addCollider(new TankWallCollider());
        this.colliderChain.addCollider(new BulletWallCollider());
        this.colliderChain.addCollider(new BulletBulletCollider());
    }

    private void addDefaultStateGenerator() {
        this.stageDeployer.addStageGenerator(new Stage1Generator());
        this.stageDeployer.addStageGenerator(new Stage2Generator());
    }
}
