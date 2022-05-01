package redcoder.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 900, GAME_HEIGHT = 900;

    private Tank myTank = new Tank(450, 600, false, Direction.UP, Group.GOOD, this);
    private ArrayList<Tank> enemyTanks = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Boom> booms = new ArrayList<>();

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
        ArrayList<Tank> tmpEnemyTanks = new ArrayList<>(enemyTanks);
        ArrayList<Bullet> tmpBullets = new ArrayList<>(bullets);
        ArrayList<Boom> tmpBooms = new ArrayList<>(booms);

        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + tmpEnemyTanks.size(), 20, 40);
        g.drawString("敌人的数量：" + tmpBullets.size(), 20, 60);
        g.drawString("爆炸的数量：" + tmpBooms.size(), 20, 80);
        g.setColor(c);


        // 我方坦克移动
        myTank.paint(g);

        // 移动敌方坦克
        for (Tank enemyTank : tmpEnemyTanks) {
            enemyTank.paint(g);
        }

        // 移动子弹
        for (Bullet bullet : tmpBullets) {
            bullet.paint(g);
        }
        // 检测子弹是否与敌方坦克发生碰撞，如果碰撞则敌方坦克消失
        for (Bullet bullet : tmpBullets) {
            for (Tank tank : tmpEnemyTanks) {
                if (bullet.collideWith(tank)) break;
            }
        }

        // 爆炸
        for (Boom boom : tmpBooms) {
            boom.paint(g);
        }
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

    public ArrayList<Boom> getBooms() {
        return booms;
    }
}
