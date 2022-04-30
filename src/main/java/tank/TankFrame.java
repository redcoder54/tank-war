package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TankFrame extends Frame {

    static final int GAME_WIDTH = 900, GAME_HEIGHT = 900;

    Tank myTank = new Tank(450, 600, Dir.UP, this);
    ArrayList<Tank> enemyTanks = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + bullets.size(), 20, 40);
        g.drawString("敌人的数量：" + enemyTanks.size(), 20, 60);
        g.setColor(c);

        myTank.paint(g);
        for (Tank tank : enemyTanks) {
            tank.paint(g);
        }

        Iterator<Bullet> bulletIt = bullets.iterator();
        while (bulletIt.hasNext()) {
            Bullet bullet = bulletIt.next();
            if (bullet.isLiving()) {
                boolean isColliding = false;
                Iterator<Tank> tankIt = enemyTanks.iterator();
                while (tankIt.hasNext()) {
                    Tank tank = tankIt.next();
                    if (bullet.collideWith(tank)) {
                        bulletIt.remove();
                        tankIt.remove();
                        isColliding = true;
                        break;
                    }
                }
                if(!isColliding) bullet.paint(g);
            } else {
                bulletIt.remove();
            }
        }
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

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
                    myTank.fire();
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
            myTank.setMoving(bL || bR || bU || bD);

            if (bL) myTank.setDir(Dir.LEFT);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bU) myTank.setDir(Dir.UP);
            if (bD) myTank.setDir(Dir.DOWN);
        }

    }
}
