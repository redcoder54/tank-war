package redcoder.tank;

import redcoder.tank.utils.SystemUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class GameSaveLoad {

    static void save(List<Object> objects) {
        File file = getFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Object obj : objects) {
                out.writeObject(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<Object> load() {
        List<Object> objects = new ArrayList<>();
        File file = getFile();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            while (obj != null) {
                objects.add(obj);
                obj = in.readObject();
            }
        } catch (EOFException e) {
            // ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    private static File getFile() {
        String userHome = SystemUtils.getUserHome();
        File dir = new File(userHome, "tank-war");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return new File(dir, "tank.snapshot");
    }

}
