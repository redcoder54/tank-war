package redcoder.tank;

import redcoder.tank.gameobj.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DirectionKeyListener extends KeyAdapter {

    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    private TankGame tankGame;

    public DirectionKeyListener(TankGame tankGame) {
        this.tankGame = tankGame;
    }

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
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_CONTROL:
                tankGame.getPlayerTank().fire();
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
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            default:
                break;
        }
        setTankDir();
    }

    void setTankDir() {
        Tank myTank = tankGame.getPlayerTank();

        myTank.setMoving(bL || bR || bU || bD);

        if (bL) myTank.setDirection(Direction.LEFT);
        if (bR) myTank.setDirection(Direction.RIGHT);
        if (bU) myTank.setDirection(Direction.UP);
        if (bD) myTank.setDirection(Direction.DOWN);
    }

}
