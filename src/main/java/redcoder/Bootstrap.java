package redcoder;

import redcoder.tank.*;

public class Bootstrap {

    public static void main(String[] args) {
        try {
            ConfigManager configManager = ConfigManager.getInstance();

            TankFrame tankFrame = new TankFrame(configManager.getGameWindowsWidth(), configManager.getGameWindowsHeight());
            tankFrame.setLocationRelativeTo(null);

            // 初始化敌方坦克
            for (int i = 0; i < configManager.getInitialTankCount(); i++) {
                tankFrame.getAiTanks()
                        .add(new Tank(30 + i * 100, 100, configManager.getAITankSpeed(), Direction.DOWN, Group.BAD, tankFrame, true));
            }

            // 启动背景音乐
            // new Thread(()->new Audio("audio/background-music.wav").play()).start();

            while (true) {
                Thread.sleep(50);
                tankFrame.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
