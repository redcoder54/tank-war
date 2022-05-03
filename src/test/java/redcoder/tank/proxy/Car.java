package redcoder.tank.proxy;

import java.util.Random;

public class Car implements Movable{

    @Override
    public void move() {
        System.out.println("car car car....");
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
