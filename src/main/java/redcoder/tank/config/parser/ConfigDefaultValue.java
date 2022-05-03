package redcoder.tank.config.parser;

final class ConfigDefaultValue {

    private ConfigDefaultValue() {
    }

    public static final String GAME_WINDOWS_WIDTH = "900";
    public static final String GAME_WINDOWS_HEIGHT = "600";
    public static final String INITIAL_TANK_COUNT = "9";
    public static final String PLAYER_TANK_SPEED = "20";
    public static final String ENEMY_TANK_SPEED = "20";
    public static final String PLAYER_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    public static final String ENEMY_FIRE_STRATEGY = "redcoder.tank.fire.BulletFireStrategy";
    public static final String COLLIDER_CHAIN = "redcoder.tank.collider.BulletTankCollider, " +
            "redcoder.tank.collider.TankTankCollider, " +
            "redcoder.tank.collider.TankWallCollider, " +
            "redcoder.tank.collider.BulletWallCollider, " +
            "redcoder.tank.collider.BulletBulletCollider";
    public static final String TANK_PRODUCER = "redcoder.tank.tankegenaretor.DefaultTankProducer";
}
