package redcoder.tank.proxy;

public class Proxy {

    public static Movable createProxy(Movable movable, InvocationHandler invocationHandler) {
        return new Movable() {
            @Override
            public void move() {
                invocationHandler.process(movable);
            }
        };
    }
}
