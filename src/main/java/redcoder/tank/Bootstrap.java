package redcoder.tank;

public class Bootstrap {

    public static void main(String[] args) {
        try {
            TankFrame tankFrame = new TankFrame();
            tankFrame.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
