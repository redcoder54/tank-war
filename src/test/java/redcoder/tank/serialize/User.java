package redcoder.tank.serialize;

import java.io.Serializable;

public class User implements Serializable {

    public static int i = 100;

    private String name;
    private int age;

    public User(String name, int age) {
        System.out.println("constructor....");
        this.name = name;
        this.age = age;
    }

    public void print(){
        System.out.printf("i=%s, name=%s, age=%s%n", i, name, age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
