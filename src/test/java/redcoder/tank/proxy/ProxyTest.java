package redcoder.tank.proxy;

import org.junit.Test;

import java.lang.reflect.Method;

public class ProxyTest {

    @Test
    public void proxyCar() {
        Movable car = new Car();

        Movable proxyCar = Proxy.createProxy(car, new InvocationHandler() {
            @Override
            public Object process(Movable target, Method method, Object[] args) {
                long t1 = System.currentTimeMillis();
                target.move();
                long t2 = System.currentTimeMillis();
                System.out.println((t2 - t1) + " ms");
                return null;
            }
        });
        proxyCar.move();

        System.out.println();
        Movable proxyProxyCar = Proxy.createProxy(proxyCar, new InvocationHandler() {
            @Override
            public Object process(Movable target, Method method, Object[] args) {
                System.out.println("start....");
                target.move();
                System.out.println("stop.....");
                return null;
            }
        });
        proxyProxyCar.move();
    }
}
