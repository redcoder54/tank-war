package redcoder.tank.stage;

import redcoder.tank.TankGameContext;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class FirstStageGenerator extends AbstractStageGenerator {

    public FirstStageGenerator() {
        super("FirstStageGenerator");
    }

    @Override
    public void generate(TankGameContext tgc) {
        // 初始化障碍物-墙
        int width = tgc.getWidth();
        int height = tgc.getHeight();

        for (int i = 0; i < 5; i++) {
            tgc.addGameObj(new Wall(100, 200, 50, 100));
            tgc.addGameObj(new Wall(width - 100, 200, 50, 100));
            tgc.addGameObj(new Wall(width / 2, 200, 50, 30));
            tgc.addGameObj(new Wall(width / 2, height - 200, 50, 30));
        }
    }
}
