package redcoder.tank.stage.generator;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class Stage1Generator extends StageGeneratorBase {

    public Stage1Generator() {
        super("Stage1Generator");
    }

    @Override
    public void generate(TGC tgc) {
        // 添加墙
        tgc.addGameObj(new Wall(180, 180));
        tgc.addGameObj(new Wall(180, 240));
        tgc.addGameObj(new Wall(180, 300));

        tgc.addGameObj(new Wall(720, 180));
        tgc.addGameObj(new Wall(720, 240));
        tgc.addGameObj(new Wall(720, 300));

        // 添加坦克
        tgc.getTankProducer().produce(tgc);
    }
}
