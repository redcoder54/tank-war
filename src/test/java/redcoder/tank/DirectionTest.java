package redcoder.tank;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void getOppositeDirection() {
        Assert.assertEquals(Direction.LEFT, Direction.getOppositeDirection(Direction.RIGHT));
        Assert.assertEquals(Direction.RIGHT, Direction.getOppositeDirection(Direction.LEFT));
        Assert.assertEquals(Direction.UP, Direction.getOppositeDirection(Direction.DOWN));
        Assert.assertEquals(Direction.DOWN, Direction.getOppositeDirection(Direction.UP));
    }
}
