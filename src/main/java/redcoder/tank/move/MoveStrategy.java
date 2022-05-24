package redcoder.tank.move;

import java.io.Serializable;

public interface MoveStrategy<T> extends Serializable {

    void move(T t);
}
