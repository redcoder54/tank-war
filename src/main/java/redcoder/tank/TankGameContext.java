package redcoder.tank;

import redcoder.tank.collider.*;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.stage.DefaultStageDeployer;
import redcoder.tank.stage.FirstStageGenerator;
import redcoder.tank.stage.StageDeployer;
import redcoder.tank.stage.StageGenerator;
import redcoder.tank.tankegenaretor.TankProducer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TankGameContext {

    private static final TankGameContext TGC = new TankGameContext();

    private int width;
    private int height;
    private Tank playerTank;
    private List<GameObj> gameObjs = new ArrayList<>();
    private ColliderChain colliderChain = new ColliderChain("defaultColliderChain");
    private StageDeployer stageDeployer = new DefaultStageDeployer();
    private GameConfig gameConfig = GameConfigFactory.getGameConfig();
    private TankProducer tankProducer;

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

    private void initTGC() {

        // 玩家坦克
        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        playerTank = new Tank(width / 2, height - 100, playerTankSpeed, Direction.UP,
                Group.GOOD, false);
        addGameObj(playerTank);

        configureCollider();
        configureStateGenerator();
        configureTankProducer();
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

    private void configureStateGenerator() {
        // 添加默认的关卡生成器
        addDefaultStateGenerator();

        // 添加自定义的关卡生成器
        List<StageGenerator> customStateGenerators = gameConfig.getCustomStateGenerators();
        if (customStateGenerators != null && !customStateGenerators.isEmpty()) {
            customStateGenerators.forEach(t -> stageDeployer.addStageGenerator(t));
        }
        // 启动关卡部署器
        new Thread(stageDeployer, "StageDeployer").start();
    }

    private void configureTankProducer() {
        // 启动坦克生产者, 生成敌方坦克
        tankProducer = gameConfig.getTankProducer();
        new Thread(tankProducer, "TankGenerator").start();
    }

    private void addDefaultCollider() {
        this.colliderChain.addCollider(new BulletTankCollider());
        this.colliderChain.addCollider(new TankTankCollider());
        this.colliderChain.addCollider(new TankWallCollider());
        this.colliderChain.addCollider(new BulletWallCollider());
        this.colliderChain.addCollider(new BulletBulletCollider());
    }

    private void addDefaultStateGenerator() {
        this.stageDeployer.addStageGenerator(new FirstStageGenerator());
    }
}
