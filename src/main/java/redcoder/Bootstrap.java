package redcoder;

import redcoder.tank.*;

public class Bootstrap {

    public static void main(String[] args) {
        try {
            TankFrame tankFrame = new TankFrame();
            tankFrame.setLocationRelativeTo(null);

            // 启动背景音乐
            new Thread(()->new Audio("audio/background-music.wav").play()).start();

            while (true) {
                Thread.sleep(50);
                tankFrame.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
