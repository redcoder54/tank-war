package redcoder.tank;

import redcoder.tank.config.GameConfigFactory;

import java.awt.*;

/**
 * 游戏进度
 */
public class GameProgress {

    // 第几关
    private int currentStage;
    // 坦克总数量
    private int totalTankCount;
    // 消灭坦克数量
    private int diedTankCount;

    public GameProgress() {
        incrementStage();
    }

    public void paint(Graphics g) {
        Color gColor = g.getColor();

        TankGameContext tankGameContext = TankGameContext.getTankGameContext();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, tankGameContext.getWidth(), 50);
        g.setColor(Color.GREEN);
        g.drawString(String.format("第%s关，剩余的敌人：%s，已消灭的敌人：%s", currentStage, totalTankCount - diedTankCount, diedTankCount), 5, 40);

        g.setColor(gColor);
    }

    /**
     * 本关游戏是否通过
     */
    public boolean isPass() {
        return diedTankCount >= totalTankCount;
    }

    public void incrementStage() {
        currentStage++;
        totalTankCount = GameConfigFactory.getGameConfig().getInitialTankCount();
        diedTankCount = 0;
    }

    public void diedTankCountIncr() {
        diedTankCount++;
    }
}
