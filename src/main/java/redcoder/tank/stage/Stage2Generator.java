package redcoder.tank.stage;

import redcoder.tank.TankGameContext;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class Stage2Generator extends AbstractStageGenerator {

    public Stage2Generator() {
        super("Stage2Generator");
    }

    @Override
    public void generate(TankGameContext tgc) {
        // 添加墙
        int width = tgc.getWidth();
        tgc.addGameObj(new Wall(width / 2, 400));
        tgc.addGameObj(new Wall(width / 2, 455));

        // 添加坦克
        tgc.getTankProducer().produce(tgc);
    }
}
