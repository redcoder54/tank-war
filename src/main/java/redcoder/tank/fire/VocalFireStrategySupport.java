package redcoder.tank.fire;

import redcoder.tank.audio.AudioPlayer;
import redcoder.tank.gameobj.Group;
import redcoder.tank.gameobj.Tank;

public class VocalFireStrategySupport {

    protected void fireSound(Tank tank) {
        if (Group.GOOD == tank.getGroup()) {
            AudioPlayer.playOnce("audio/tank_fire.wav");
        }
    }
}
