package redcoder.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankFrame extends Frame {

    private Tank playerTank;
    private ArrayList<Tank> aiTanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<Boom> booms;

    public TankFrame(int width, int height) throws HeadlessException {
        if (width < 0) {
            throw new IllegalArgumentException("Game windows width must grant than 0");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Game windows height must grant than 0");
        }

        int playerTankSpeed = ConfigManager.getInstance().getPlayerTankSpeed();
        playerTank = new Tank(width / 2, height - 100, playerTankSpeed, Direction.UP, Group.GOOD, this, false);
        aiTanks = new ArrayList<>();
        bullets = new ArrayList<>();
        booms = new ArrayList<>();

        setSize(width, height);
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
            offScreenImage = this.createImage(getWidth(), getHeight());
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, getWidth(), getHeight());
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        ArrayList<Tank> tmpEnemyTanks = new ArrayList<>(aiTanks);
        ArrayList<Bullet> tmpBullets = new ArrayList<>(bullets);
        ArrayList<Boom> tmpBooms = new ArrayList<>(booms);

        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("敌人的数量：" + tmpEnemyTanks.size(), 20, 40);
        g.drawString("子弹的数量：" + tmpBullets.size(), 20, 60);
        g.drawString("爆炸的数量：" + tmpBooms.size(), 20, 80);
        g.setColor(c);

        // 我方坦克移动
        playerTank.paint(g);

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

    public Tank getPlayerTank() {
        return playerTank;
    }

    public ArrayList<Tank> getAiTanks() {
        return aiTanks;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Boom> getBooms() {
        return booms;
    }
}
