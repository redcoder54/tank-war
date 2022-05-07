package redcoder.tank;

import java.awt.*;

/**
 * 游戏进度
 */
public class GameProgress {

    private static final int INITIAL_TANK_COUNT;
    private final static Font FONT = new Font(null, Font.BOLD, 14);

    static {
        INITIAL_TANK_COUNT = GameConfigFactory.getGameConfig().getInitialTankCount();
    }

    // 第几关
    private int currentStage;
    // 敌方坦克总数量
    private int totalTankCount;
    // 存活的坦克数量
    private int livingTankCount;
    // 消灭坦克的数量
    private int diedTankCount;

    public GameProgress() {
        nextStage();
    }

    public void paint(Graphics g) {
        TGC tgc = TGC.getTGC();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, TGC.WIDTH, 50);
        g.setColor(Color.GREEN);
        g.setFont(FONT);
        String str = String.format("第%s关，总数量：%s，存活：%s，消灭：%s", currentStage, totalTankCount, livingTankCount, diedTankCount);
        g.drawString(str, 5, 40);
    }

    /**
     * 本关游戏是否通过
     */
    public boolean isPass() {
        return diedTankCount >= totalTankCount;
    }

    /**
     * 进入下一关
     */
    public void nextStage() {
        currentStage++;
        totalTankCount = INITIAL_TANK_COUNT;
        diedTankCount = 0;
        livingTankCount = 0;
    }

    public void diedTankCountIncr() {
        diedTankCount++;
        livingTankCount--;
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
}
