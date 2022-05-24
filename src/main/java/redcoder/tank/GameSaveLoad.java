package redcoder.tank;

import redcoder.tank.model.GameModel;
import redcoder.tank.utils.SystemUtils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSaveLoad {

    private static final Logger LOGGER = Logger.getLogger(GameSaveLoad.class.getName());

    public static void save(GameModel gameModel) {
        File file = getFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(gameModel);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "GameSaveLoad.save", e);
        }
    }

    public static GameModel load() {
        File file = getFile();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            return (GameModel) obj;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "GameSaveLoad.load", e);
        }
        return null;
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
