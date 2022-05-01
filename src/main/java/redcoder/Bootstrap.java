package redcoder;

import redcoder.tank.Direction;
import redcoder.tank.Group;
import redcoder.tank.Tank;
import redcoder.tank.TankFrame;

public class Bootstrap {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        // 初始化敌方坦克
        for (int i = 0; i < 9; i++) {
            tankFrame.getEnemyTanks()
                    .add(new Tank(30 + i * 100, 100, 15, true, Direction.DOWN, Group.BAD, tankFrame, true));
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
