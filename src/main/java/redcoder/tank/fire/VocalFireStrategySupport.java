package redcoder.tank.fire;

import redcoder.tank.Audio;
import redcoder.tank.Group;
import redcoder.tank.gameobj.Tank;

public class VocalFireStrategySupport {

    protected void fireSound(Tank tank) {
        if (Group.GOOD == tank.getGroup()) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
