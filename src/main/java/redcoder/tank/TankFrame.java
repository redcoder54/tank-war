package redcoder.tank;

import redcoder.tank.gameobj.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends JFrame {

    public TankFrame() {
        super("坦克大战");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(906, 655);
        setLocationRelativeTo(null);
        addKeyListener(new DirectionKeyListener());
    }

    public void startup() {
        // fixme: bgm
        // Audio bgm = new Audio("audio/background-music.wav");
        // new Thread(bgm::loop).start();

        JLabel progressLabel = new JLabel("loading...");
        progressLabel.setOpaque(true);
        progressLabel.setBackground(Color.GRAY);
        progressLabel.setForeground(Color.GREEN);
        progressLabel.setFont(new Font(null, Font.BOLD, 20));

        TankPanel tankPanel = new TankPanel(progressLabel);

        setLayout(new BorderLayout());
        add(progressLabel, BorderLayout.NORTH);
        add(tankPanel, BorderLayout.CENTER);

        setVisible(true);
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
                        TGC.getTGC().resetTGC();
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
