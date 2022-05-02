package redcoder.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private TankGame tankGame;
    private Image offScreenImage;

    public TankFrame(TankGame tankGame) throws HeadlessException {
        this.tankGame = tankGame;

        setSize(tankGame.getWidth(), tankGame.getHeight());
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addKeyListener(new DirectionKeyListener(tankGame));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        offScreenImage = this.createImage(getWidth(), getHeight());
    }

    @Override
    public void update(Graphics g) {
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, getWidth(), getHeight());
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        tankGame.paint(g);
    }
}
