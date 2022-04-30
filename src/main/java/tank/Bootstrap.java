package tank;

public class Bootstrap {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 9; i++) {
            tankFrame.enemyTanks.add(new Tank(30 + i * 100, 100, Dir.DOWN, tankFrame));
        }

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}
