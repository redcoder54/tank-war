package redcoder.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 900, GAME_HEIGHT = 900;

    private Tank myTank = new Tank(450, 600, Dir.UP, Group.GOOD, this);
    private ArrayList<Tank> enemyTanks = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    // private Boom boom = new Boom(300, 300);

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addKeyListener(new DirectionKeyListener(this));

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
                // 检测子弹是否与敌方坦克发生碰撞，如果碰撞则敌方坦克消失
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
                if (!isColliding) bullet.paint(g);
            } else {
                bulletIt.remove();
            }
        }

        // boom.paint(g);
    }

    public Tank getMyTank() {
        return myTank;
    }

    public ArrayList<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
