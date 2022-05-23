package redcoder.tank.stage.generator;

import redcoder.tank.TGC;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class Stage2Generator extends StageGeneratorBase {

    public Stage2Generator() {
        super("Stage2Generator");
    }

    @Override
    public void generate(TGC tgc) {
        // 添加墙
        tgc.addGameObj(new Wall(360,180));
        tgc.addGameObj(new Wall(420,180));
        tgc.addGameObj(new Wall(480,180));

        tgc.addGameObj(new Wall(360,420));
        tgc.addGameObj(new Wall(420,420));
        tgc.addGameObj(new Wall(480,420));

        // 添加坦克
        tgc.getTankProducer().produce(tgc);
    }
}
