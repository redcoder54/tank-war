package redcoder.tank.gameobj;

import redcoder.tank.audio.AudioPlayer;
import redcoder.tank.model.GameModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Boom extends GameObj {

    private int step = 0;

    public Boom(int x, int y) {
        super(x, y);
        AudioPlayer.playOnce("audio/boom.wav");
    }

    @Override
    public void paint(Graphics g, GameModel gameModel) {
        g.drawImage(BOOMS[step], x, y, null);
        step = pause ? step : step + 1;

        if (step >= BOOMS.length) {
            gameModel.removeGameObj(this);
        }
    }

    private static BufferedImage[] BOOMS = new BufferedImage[16];
    static {
        try {
            ClassLoader classLoader = Boom.class.getClassLoader();
            for (int i = 0; i < BOOMS.length; i++) {
                BOOMS[i] = ImageIO.read(classLoader.getResourceAsStream(String.format("images/boom/boom%d.gif", i + 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
