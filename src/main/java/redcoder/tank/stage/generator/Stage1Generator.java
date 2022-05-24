package redcoder.tank.stage.generator;

import redcoder.tank.model.GameModel;
import redcoder.tank.gameobj.Wall;
import redcoder.tank.producer.ResettableTankProducer;

/**
 * 第一关
 */
public class Stage1Generator extends StageGeneratorBase {

    public Stage1Generator() {
        super("Stage1Generator");
    }

    @Override
    public void generate(GameModel gameModel) {
        // 添加墙
        gameModel.addGameObj(new Wall(180, 180));
        gameModel.addGameObj(new Wall(180, 240));
        gameModel.addGameObj(new Wall(180, 300));

        gameModel.addGameObj(new Wall(720, 180));
        gameModel.addGameObj(new Wall(720, 240));
        gameModel.addGameObj(new Wall(720, 300));

        // 委托生产者生产坦克
        ResettableTankProducer tankProducer = gameModel.getTankProducer();
        tankProducer.reset();
        tankProducer.produce(gameModel);
    }
}
