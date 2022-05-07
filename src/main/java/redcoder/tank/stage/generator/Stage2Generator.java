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
        tgc.addGameObj(new Wall(TGC.WIDTH / 2, 400));
        tgc.addGameObj(new Wall(TGC.WIDTH / 2, 455));

        // 添加坦克
        tgc.getTankProducer().produce(tgc);
    }
}
