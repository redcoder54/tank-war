package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

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
    public void deploy(TankGameContext tgc) {
        stageGenerators.get(generatorCounter++).generate(tgc);
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
