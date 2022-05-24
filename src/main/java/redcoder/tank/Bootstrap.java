package redcoder.tank;

import redcoder.tank.audio.AudioPlayer;
import redcoder.tank.log.LoggingUtils;

public class Bootstrap {

    public static void main(String[] args) {
        try {
            LoggingUtils.resetLogManager();
            AudioPlayer.playLoop("audio/background-music.wav");

            TankFrame tankFrame = new TankFrame();
            tankFrame.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
