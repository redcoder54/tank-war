package redcoder.tank.stage.generator;

import redcoder.tank.GameModel;
import redcoder.tank.gameobj.Wall;

/**
 * 第一关
 */
public class Stage2Generator extends StageGeneratorBase {

    public Stage2Generator() {
        super("Stage2Generator");
    }

    @Override
    public void generate(GameModel gameModel) {
        // 添加墙
        gameModel.addGameObj(new Wall(360,180));
        gameModel.addGameObj(new Wall(420,180));
        gameModel.addGameObj(new Wall(480,180));

        gameModel.addGameObj(new Wall(360,420));
        gameModel.addGameObj(new Wall(420,420));
        gameModel.addGameObj(new Wall(480,420));

        // 添加坦克
        gameModel.getTankProducer().produce(gameModel);
    }
}
