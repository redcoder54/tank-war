package redcoder.tank.proxy;

import java.lang.reflect.Method;

public class Proxy {

    public static Movable createProxy(Movable movable, InvocationHandler invocationHandler) {
        return new Movable() {
            @Override
            public void move() {
                try {
                    Class<? extends Movable> clz = movable.getClass();
                    Method method = clz.getDeclaredMethod("move");
                    invocationHandler.process(movable, method, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
