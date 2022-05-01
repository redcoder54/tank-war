package redcoder.tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DirectionKeyListener extends KeyAdapter {

    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    private TankFrame tankFrame;

    public DirectionKeyListener(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Tank myTank = tankFrame.getMyTank();
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
                myTank.fire();
                break;
            default:
                break;
        }
        setTankDir();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Tank myTank = tankFrame.getMyTank();
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
        Tank myTank = tankFrame.getMyTank();

        myTank.setMoving(bL || bR || bU || bD);

        if (bL) myTank.setDir(Direction.LEFT);
        if (bR) myTank.setDir(Direction.RIGHT);
        if (bU) myTank.setDir(Direction.UP);
        if (bD) myTank.setDir(Direction.DOWN);
    }

}
