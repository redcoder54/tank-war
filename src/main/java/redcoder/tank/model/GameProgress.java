package redcoder.tank.model;

import redcoder.tank.TankFrame;
import redcoder.tank.config.GameConfigFactory;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 游戏进度
 */
public class GameProgress implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(GameProgress.class.getName());
    private static final String TEMPLATE = "第%s关，总数量：%s，存活：%s，消灭：%s";
    private static final int INITIAL_TANK_COUNT = GameConfigFactory.getGameConfig().getInitialTankCount();

    // 第几关
    private int currentStage;
    // 敌方坦克总数量
    private int totalTankCount;
    // 存活的坦克数量
    private int livingTankCount;
    // 消灭坦克的数量
    private int diedTankCount;

    private boolean start = false;

    public void paint(Graphics g, TankFrame tankFrame) {
        if (!start) {
            nextStage();
            start = true;
        }
        JLabel progressLabel = tankFrame.getProgressLabel();
        String text = String.format(TEMPLATE, currentStage, totalTankCount, livingTankCount, diedTankCount);
        progressLabel.setText(text);
    }

    public void diedTankCountIncr() {
        diedTankCount++;
        livingTankCount--;

        if (diedTankCount >= totalTankCount) {
            // 本关通过, 进入下一关
            nextStage();
        }
    }

    private void nextStage() {
        currentStage++;
        totalTankCount = INITIAL_TANK_COUNT;
        diedTankCount = 0;
        livingTankCount = 0;

        try {
            GameModel gameModel = GameModelWrapper.getGameModel();
            gameModel.getGameStageSwitchController().nextStage(gameModel);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "GameProgress.nextStage", e);
        }
    }

    public void livingTankCountIncr() {
        livingTankCount++;
    }


    public int getCurrentStage() {
        return currentStage;
    }

    public int getTotalTankCount() {
        return totalTankCount;
    }

    public int getLivingTankCount() {
        return livingTankCount;
    }

    public int getDiedTankCount() {
        return diedTankCount;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "currentStage=" + currentStage +
                ", totalTankCount=" + totalTankCount +
                ", livingTankCount=" + livingTankCount +
                ", diedTankCount=" + diedTankCount +
                '}';
    }
}
