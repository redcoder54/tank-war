package redcoder.tank.serialize;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeTest {

    @Test
    public void serialize1() throws Exception {
        User user = new User("tom", 20);
        System.out.println("序列化之前");
        user.print();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 10);
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(user);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);
        Object o = in.readObject();
        System.out.println("序列化之后");
        ((User) o).print();
    }


    @Test
    public void serialize2() throws Exception {
        User user = new User("tom", 20);
        System.out.println("序列化之前");
        user.print();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 10);
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(user);

        User.i = 200;
        user.setName("jerry");
        user.setAge(18);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);
        Object o = in.readObject();
        System.out.println("序列化之后");
        ((User) o).print();
    }
}
