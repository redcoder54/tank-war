package redcoder.tank.model;

import redcoder.tank.TankFrame;
import redcoder.tank.TankPanel;
import redcoder.tank.collider.ColliderChain;
import redcoder.tank.gameobj.GameObj;
import redcoder.tank.gameobj.Tank;
import redcoder.tank.stage.GameStageSwitchController;
import redcoder.tank.stage.deployer.StageDeployer;
import redcoder.tank.producer.ResettableTankProducer;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class GameModel implements Serializable {

    private Tank playerTank;
    private List<GameObj> gameObjs;
    private ColliderChain colliderChain;
    private ResettableTankProducer tankProducer;
    private GameProgress gameProgress;
    private StageDeployer stageDeployer;
    private GameStageSwitchController gameStageSwitchController;
    // 表示游戏是否暂停
    private boolean pause = false;

    public void paint(Graphics g, TankFrame tankFrame) {
        if (!playerTank.isLiving()) {
            // 游戏结束
            g.setColor(Color.RED);
            g.drawString("游戏结束，按回车键重新开始", TankPanel.WIDTH / 2 - 200, TankPanel.HEIGHT / 2);
            return;
        }

        // 绘制游戏状态栏
        gameProgress.paint(g, tankFrame);

        for (GameObj gameObj : gameObjs) {
            gameObj.paint(g, this);
        }

        // 碰撞处理
        for (int i = 0; i < gameObjs.size(); i++) {
            for (int j = i + 1; j < gameObjs.size(); j++) {
                GameObj o1 = gameObjs.get(i);
                GameObj o2 = gameObjs.get(j);
                colliderChain.collide(this, o1, o2);
            }
        }

        if (pause) {
            g.setColor(Color.RED);
            g.setFont(new Font(null, Font.BOLD, 25));
            g.drawString("游戏暂停，按回车键开始", TankPanel.WIDTH / 2 - 180, TankPanel.HEIGHT / 2);
        }
    }

    /**
     * 暂停游戏
     */
    public void pause() {
        if (pause) {
            return;
        }
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
        if (!pause) {
            return;
        }
        this.pause = false;
        for (GameObj gameObj : gameObjs) {
            gameObj.resume();
        }
        tankProducer.resume();
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

    // ------------ getter setter
    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public List<GameObj> getGameObjs() {
        return gameObjs;
    }

    public void setGameObjs(List<GameObj> gameObjs) {
        this.gameObjs = gameObjs;
    }

    public ColliderChain getColliderChain() {
        return colliderChain;
    }

    public void setColliderChain(ColliderChain colliderChain) {
        this.colliderChain = colliderChain;
    }

    public ResettableTankProducer getTankProducer() {
        return tankProducer;
    }

    public void setTankProducer(ResettableTankProducer tankProducer) {
        this.tankProducer = tankProducer;
    }

    public GameProgress getGameProgress() {
        return gameProgress;
    }

    public void setGameProgress(GameProgress gameProgress) {
        this.gameProgress = gameProgress;
    }

    public StageDeployer getStageDeployer() {
        return stageDeployer;
    }

    public void setStageDeployer(StageDeployer stageDeployer) {
        this.stageDeployer = stageDeployer;
    }

    public GameStageSwitchController getGameStageSwitchController() {
        return gameStageSwitchController;
    }

    public void setGameStageSwitchController(GameStageSwitchController gameStageSwitchController) {
        this.gameStageSwitchController = gameStageSwitchController;
    }
}
