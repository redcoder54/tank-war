package redcoder.tank;

import redcoder.tank.collider.ColliderChain;
import redcoder.tank.config.GameConfig;
import redcoder.tank.config.GameConfigFactory;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.Wall;
import redcoder.tank.tankegenaretor.DefaultTankProducer;
import redcoder.tank.tankegenaretor.TankProducer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TankGame {

    private static final TankGame INSTANCE = new TankGame();

    private int width;
    private int height;
    private Tank playerTank;
    private List<GameObj> gameObjs = new ArrayList<>();
    private ColliderChain colliderChain = new ColliderChain();
    private GameConfig gameConfig = GameConfigFactory.getGameConfig();
    private TankProducer tankProducer;

    public static TankGame getInstance() {
        return INSTANCE;
    }

    private TankGame() {
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

        init();
    }

    private void init() {
        // 玩家坦克
        int playerTankSpeed = gameConfig.getPlayerTankSpeed();
        playerTank = new Tank(width / 2, height - 100, playerTankSpeed, Direction.UP,
                Group.GOOD, false);
        addGameObj(playerTank);

        // 初始化障碍物-墙
        for (int i = 0; i < 5; i++) {
            addGameObj(new Wall(100, 200, 50, 100));
            addGameObj(new Wall(width - 100, 200, 50, 100));
            addGameObj(new Wall(width / 2, 200, 50, 30));
            addGameObj(new Wall(width / 2, height - 200, 50, 30));
        }

        // 碰撞器
        colliderChain = gameConfig.getColliderChain();

        // 启动坦克生产者, 生成敌方坦克
        tankProducer = gameConfig.getTankProducer();
        new Thread(tankProducer, "TankGenerator").start();
    }

    public void paint(Graphics g) {
        // playerTank.paint(g);
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
}
