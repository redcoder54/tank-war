package redcoder.tank.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {

    Object process(Movable target, Method method, Object[] args);
}
