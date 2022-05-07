package redcoder.tank;

import redcoder.tank.gameobj.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private Image offScreenImage;

    public TankFrame() throws HeadlessException {
        setSize(TGC.WIDTH, TGC.HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addKeyListener(new DirectionKeyListener());

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
        gOffScreen.setColor(TGC.BACKGROUND_COLOR);
        gOffScreen.fillRect(0, 0, getWidth(), getHeight());
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        TGC.getTGC().paint(g);
    }

    static class DirectionKeyListener extends KeyAdapter {

        private boolean bL = false;
        private boolean bU = false;
        private boolean bR = false;
        private boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            TGC tgc = TGC.getTGC();
            int kc = e.getKeyCode();
            switch (kc) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_SPACE:
                    tgc.getPlayerTank().fire();
                    break;
                default:
                    break;
            }
            setTankDir(tgc);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            TGC tgc = TGC.getTGC();
            int kc = e.getKeyCode();
            switch (kc) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_ENTER:
                    if (!tgc.getPlayerTank().isLiving()) {
                        TGC.resetTGC();
                    } else if (tgc.isPause()) {
                        tgc.resume();
                    } else {
                        tgc.pause();
                    }
                    break;
                default:
                    break;
            }
            setTankDir(tgc);
        }

        void setTankDir(TGC tgc) {
            Tank myTank = tgc.getPlayerTank();
            myTank.setMoving(bL || bR || bU || bD);
            if (bL) myTank.setDirection(Direction.LEFT);
            if (bR) myTank.setDirection(Direction.RIGHT);
            if (bU) myTank.setDirection(Direction.UP);
            if (bD) myTank.setDirection(Direction.DOWN);
        }

    }
}
