package redcoder.tank.fire;

import redcoder.tank.Audio;
import redcoder.tank.Group;
import redcoder.tank.Tank;

public abstract class VocalFireStrategySupport implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        if (Group.GOOD == tank.getGroup()) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
        doFire(tank);
    }

    protected abstract void doFire(Tank tank);
}
