package redcoder.tank;

import redcoder.tank.gameobj.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class DirectionKeyListener extends KeyAdapter {

    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();
        switch (kc) {
            case KeyEvent.VK_W:
                bU = true;
                break;
            case KeyEvent.VK_S:
                bD = true;
                break;
            case KeyEvent.VK_A:
                bL = true;
                break;
            case KeyEvent.VK_D:
                bR = true;
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_K:
                TGC.getTGC().getPlayerTank().fire();
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
            case KeyEvent.VK_W:
                bU = false;
                break;
            case KeyEvent.VK_S:
                bD = false;
                break;
            case KeyEvent.VK_A:
                bL = false;
                break;
            case KeyEvent.VK_D:
                bR = false;
                break;
            case KeyEvent.VK_ENTER:
                TGC tgc = TGC.getTGC();
                if (tgc.isStop()) {
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
        setTankDir();
    }

    void setTankDir() {
        TGC tgc = TGC.getTGC();
        if (tgc.isPause()) {
            return;
        }
        Tank myTank = tgc.getPlayerTank();
        myTank.setMoving(bL || bR || bU || bD);
        if (bL) myTank.setDirection(Direction.LEFT);
        if (bR) myTank.setDirection(Direction.RIGHT);
        if (bU) myTank.setDirection(Direction.UP);
        if (bD) myTank.setDirection(Direction.DOWN);
    }

}
