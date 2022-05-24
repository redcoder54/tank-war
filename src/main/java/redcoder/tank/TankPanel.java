package redcoder.tank;

import redcoder.tank.gameobj.Tank;
import redcoder.tank.utils.ScheduledUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class TankPanel extends JPanel {

    public final static int WIDTH = 900;
    public final static int HEIGHT = 600;

    private final TankFrame tankFrame;
    private GameModel gameModel;

    public TankPanel(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
        this.gameModel = GameModelFactory.getGameModel();

        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new DirectionKeyListener());
        ScheduledUtils.scheduleAtFixedRate(this::repaint, 50, 50, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameModel.paint(g, tankFrame);
        g.setFont(new Font(null, Font.BOLD, 25));
    }

    class DirectionKeyListener extends KeyAdapter {

        private boolean bL = false;
        private boolean bU = false;
        private boolean bR = false;
        private boolean bD = false;

        private boolean pause = false;

        @Override
        public void keyPressed(KeyEvent e) {
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
                    gameModel.getPlayerTank().fire();
                    break;
                case KeyEvent.VK_ENTER:
                    if (!gameModel.getPlayerTank().isLiving()) {
                        gameModel = GameModelFactory.getGameModel();
                    } else if (pause) {
                        pause = false;
                        gameModel.resume();
                    } else {
                        pause = true;
                        gameModel.pause();
                    }
                    break;
                default:
                    break;
            }
            setTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
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
                default:
                    break;
            }
            setTankDir();
        }

        void setTankDir() {
            Tank myTank = gameModel.getPlayerTank();
            myTank.setMoving(bL || bR || bU || bD);
            if (bL) myTank.setDirection(Direction.LEFT);
            if (bR) myTank.setDirection(Direction.RIGHT);
            if (bU) myTank.setDirection(Direction.UP);
            if (bD) myTank.setDirection(Direction.DOWN);
        }
    }
}
