package redcoder.tank.stage.generator;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class Stage1Generator extends AbstractStageGenerator {

    public Stage1Generator() {
        super("Stage1Generator");
    }

    @Override
    public void generate(TGC tgc) {
        // 添加墙
        tgc.addGameObj(new Wall(100, 200));
        tgc.addGameObj(new Wall(160, 200));

        // 添加坦克
        tgc.getTankProducer().produce(tgc);
    }
}
