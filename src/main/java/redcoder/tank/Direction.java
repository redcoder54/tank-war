package redcoder.tank;

public enum Direction {
    LEFT, UP, RIGHT, DOWN;

    public static final Direction[][] DIRECTION_PAIR = {{LEFT, RIGHT}, {UP, DOWN}};

    public static Direction[] getDirections(Direction exclude) {
        Direction[] directions = new Direction[values().length - 1];
        int i = 0;
        for (Direction direction : values()) {
            if (direction != exclude) {
                directions[i++] = direction;
            }
        }
        return directions;
    }

    public static Direction getOppositeDirection(Direction direction) {
        for (Direction[] directions : DIRECTION_PAIR) {
            Direction d1 = directions[0];
            Direction d2 = directions[1];
            if (d1 == direction && d2 != direction) {
                return d2;
            } else if (d1 != direction && d2 == direction) {
                return d1;
            }
        }
        throw new IllegalArgumentException(String.format("未找到与%s相反的方向", direction.name()));
    }
}
