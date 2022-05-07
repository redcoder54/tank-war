package redcoder.tank;

import redcoder.tank.collider.*;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.stage.DefaultGameStageSwitchController;
import redcoder.tank.stage.GameStageSwitchController;
import redcoder.tank.stage.deployer.CyclicStageDeployer;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.stage.generator.Stage1Generator;
import redcoder.tank.stage.generator.Stage2Generator;
import redcoder.tank.stage.generator.StageGenerator;
import redcoder.tank.tankproducer.TankProducer;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TGC {

    /**
     * 游戏背景颜色
     */
    public final static Color BACKGROUND_COLOR = Color.BLACK;
    /**
     * 游戏默认字体
     */
    public final static Font DEFAULT_FONT = new Font(null, Font.BOLD, 25);

    public final static int WIDTH =900;
    public final static int HEIGHT =600;

    private final static ReentrantLock LOCK = new ReentrantLock();
    private static TGC instance = new TGC();

    private Tank playerTank;
    private List<GameObj> gameObjs;
    private ColliderChain colliderChain;
    private TankProducer tankProducer;
    private StageDeployer stageDeployer;
    private GameStageSwitchController gameStageSwitchController;
    private GameProgress gameProgress;
    // 表示游戏是否结束
    private boolean stop = false;
    // 表示游戏是否暂停
    private boolean pause = false;

    public static TGC getTGC() {
        return instance;
    }

    static void resetTGC() {
        if (LOCK.tryLock()) {
            try {
                instance = new TGC();
            } finally {
                LOCK.unlock();
            }
        }
    }

    private TGC() {
        initTGC();
    }

    public void paint(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.setFont(DEFAULT_FONT);

        if (stop) {
            // 游戏结束
            g.setColor(Color.RED);
            g.drawString("游戏结束，按回车键重新开始", WIDTH / 2 - 200, HEIGHT / 2);
            return;
        }

        // 绘制游戏状态栏
        gameProgress.paint(g);

        for (GameObj gameObj : gameObjs) {
            gameObj.paint(g);
        }

        // 碰撞处理
        for (int i = 0; i < gameObjs.size(); i++) {
            for (int j = i + 1; j < gameObjs.size(); j++) {
                GameObj o1 = gameObjs.get(i);
                GameObj o2 = gameObjs.get(j);
                colliderChain.collide(o1, o2);
            }
        }

        if (pause) {
            g.setColor(Color.RED);
            g.setFont(DEFAULT_FONT);
            g.drawString("游戏暂停，按回车键开始", WIDTH / 2 - 180, HEIGHT / 2);
        }
    }

    /**
     * 结束游戏
     */
    public void stop() {
        stop = true;
    }

    /**
     * 游戏是否结束
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * 暂停游戏
     */
    public void pause() {
        this.pause = true;
        for (GameObj gameObj : gameObjs) {
            gameObj.pause();
        }
        tankProducer.pause();
    }

    /**
     * 恢复游戏
     */
    public void resume() {
        this.pause = false;
        for (GameObj gameObj : gameObjs) {
            gameObj.resume();
        }
        tankProducer.resume();
    }

    /**
     * 游戏是否暂停
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * 重置玩家坦克
     */
    public void resetPlayerTank() {
        GameConfig gameConfig = GameConfig.getGameConfig();
        configurePlayerTank(gameConfig);
    }

    /**
     * 清除游戏内的所有物体
     */
    public void clearGameObj() {
        this.gameObjs.clear();
    }

    /**
     * 添加游戏物体
     */
    public void addGameObj(GameObj gameObj) {
        this.gameObjs.add(gameObj);
    }

    /**
     * 移除游戏物体
     */
    public void removeGameObj(GameObj gameObj) {
        this.gameObjs.remove(gameObj);
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

    // ----------- initialize TGC
    private void initTGC() {
        GameConfig gameConfig = GameConfig.getGameConfig();

        gameObjs = new CopyOnWriteArrayList<>();
        colliderChain = new ColliderChain("defaultColliderChain");
        tankProducer = gameConfig.getTankProducer();
        stageDeployer = new CyclicStageDeployer();
        gameStageSwitchController = new DefaultGameStageSwitchController(stageDeployer);

        configurePlayerTank(gameConfig);
        configureCollider(gameConfig);
        configureStageGenerator(gameConfig);

        // 启动游戏关卡切换控制器
        gameProgress = gameStageSwitchController.start(this);
    }

    private void configurePlayerTank(GameConfig gameConfig) {
        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        playerTank = new Tank(WIDTH / 2, HEIGHT - Tank.HEIGHT, playerTankSpeed, Direction.UP,
                Group.GOOD, false);
        addGameObj(playerTank);
    }

    private void configureCollider(GameConfig gameConfig) {
        // 添加默认的碰撞器
        addDefaultCollider();

        // 添加自定义的碰撞器
        List<Collider> customColliders = gameConfig.getCustomColliders();
        if (customColliders != null && !customColliders.isEmpty()) {
            customColliders.forEach(t -> colliderChain.addCollider(t));
        }
    }

    private void configureStageGenerator(GameConfig gameConfig) {
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
