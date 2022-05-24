package redcoder.tank;

import redcoder.tank.gameobj.Tank;
import redcoder.tank.utils.ScheduledUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class TankPanel extends JPanel {

    private final JLabel progressLabel;

    public TankPanel(JLabel progressLabel) {
        this.progressLabel = progressLabel;
        setFocusable(true);
        addKeyListener(new DirectionKeyListener());
        ScheduledUtils.scheduleAtFixedRate(this::repaint, 50, 50, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TGC.getTGC().paint(g, progressLabel);
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
