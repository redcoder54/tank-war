package redcoder.tank.proxy;

public interface InvocationHandler {

    Object process(Movable target);
}
