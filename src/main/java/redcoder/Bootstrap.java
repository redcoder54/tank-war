package redcoder;

import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

public class Bootstrap {

    private static final int ENEMY_TANK_NUM = 9;

    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        tankFrame.setLocationRelativeTo(null);

        // 初始化敌方坦克
        for (int i = 0; i < ENEMY_TANK_NUM; i++) {
            tankFrame.getEnemyTanks()
                    .add(new Tank(30 + i * 100, 100, 2, true, Direction.DOWN, Group.BAD, tankFrame));
        }

        // 启动背景音乐
        // new Thread(()->new Audio("audio/background-music.wav").play()).start();

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}
