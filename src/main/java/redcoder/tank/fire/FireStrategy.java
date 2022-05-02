package redcoder.tank.fire;

import redcoder.tank.gameobj.Tank;

/**
 * 策略类，坦克如何开火
 */
public interface FireStrategy {

    String BULLET = "bullet";
    String FOUR_DIRECTION_BULLET = "fourDirectionBullet";
    String MISSILE = "missile";
    String FOUR_DIRECTION_MISSILE = "fourDirectionMissile";

    void fire(Tank tank);
}
