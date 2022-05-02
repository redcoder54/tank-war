package redcoder.tank;

import redcoder.tank.collider.Collider;
import redcoder.tank.collider.ColliderChain;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.gameobj.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankGame {

    private int width;
    private int height;
    private Tank playerTank;
    private List<GameObj> gameObjs;
    private ColliderChain colliderChain = new ColliderChain();
    private Random random = new Random();

    public TankGame() {
        ConfigManager configManager = ConfigManager.getInstance();

        int gameWindowsWidth = configManager.getGameWindowsWidth();
        int gameWindowsHeight = configManager.getGameWindowsHeight();
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
        this.gameObjs = new ArrayList<>();

        init(configManager);
    }

    private void init(ConfigManager configManager) {
        // 玩家坦克
        int playerTankSpeed = ConfigManager.getInstance().getPlayerTankSpeed();
        playerTank = new Tank(width / 2, height - 100, playerTankSpeed, Direction.UP,
                Group.GOOD, this, false);
        addGameObj(playerTank);
        // 初始化敌方坦克
        for (int i = 0; i < configManager.getInitialTankCount(); i++) {
            Tank tank = new Tank(30 + i * 100, 100, configManager.getEnemyTankSpeed(),
                    Direction.DOWN, Group.BAD, this, true);
            addGameObj(tank);
        }

        // 初始化障碍物-墙
        for (int i = 0; i < 5; i++) {
            addGameObj(new Wall(100, 200, 50, 100));
            addGameObj(new Wall(width - 100, 200, 50, 100));
            addGameObj(new Wall(width / 2, 200, 50, 30));
            addGameObj(new Wall(width / 2, height - 200, 50, 30));
        }

        // 碰撞器
        for (String str : configManager.getColliderChain().split(",")) {
            try {
                Class<Collider> clazz = (Class<Collider>) Class.forName(str.trim());
                colliderChain.addCollider(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public List<GameObj> getGameObjs() {
        return gameObjs;
    }
}
