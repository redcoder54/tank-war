package redcoder.tank.stage.deployer;

import redcoder.tank.model.GameModel;
import redcoder.tank.stage.generator.StageGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 按顺序循环调用StageGenerator创建新的关卡
 */
public class CyclicStageDeployer implements StageDeployer {

    private Set<String> generatorNames = new HashSet<>();
    private List<StageGenerator> stageGenerators = new ArrayList<>();
    private int generatorCounter = 0;


    @Override
    public void deploy(GameModel gameModel) {
        stageGenerators.get(generatorCounter++).generate(gameModel);
        if (generatorCounter >= stageGenerators.size()) {
            generatorCounter = 0;
        }
    }

    @Override
    public void addStageGenerator(StageGenerator generator) {
        if (generatorNames.add(generator.getName())) {
            stageGenerators.add(generator);
        }
    }

    @Override
    public List<StageGenerator> getStageGenerators() {
        return stageGenerators;
    }
}
