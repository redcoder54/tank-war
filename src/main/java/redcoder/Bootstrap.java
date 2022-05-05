package redcoder;

import redcoder.tank.Audio;
import redcoder.tank.TankFrame;
import redcoder.utils.ScheduledUtils;

import java.util.concurrent.TimeUnit;

public class Bootstrap {

    public static void main(String[] args) {
        try {
            TankFrame tankFrame = new TankFrame();
            tankFrame.setLocationRelativeTo(null);

            // bgm
            Audio bgm = new Audio("audio/background-music.wav");
            new Thread(bgm::loop).start();

            // repaint repeatedly
            ScheduledUtils.scheduleAtFixedRate(tankFrame::repaint, 50, 50, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
