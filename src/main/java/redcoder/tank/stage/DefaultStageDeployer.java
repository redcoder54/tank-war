package redcoder.tank.stage;

import redcoder.tank.TankGameContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultStageDeployer implements StageDeployer {

    private Set<String> generatorNames = new HashSet<>();
    private List<StageGenerator> stageGenerators = new ArrayList<>();

    @Override
    public void deploy(TankGameContext tgc) {
        // TODO: 2022/5/3 实现闯关逻辑
        for (StageGenerator stageGenerator : stageGenerators) {
            stageGenerator.generate(tgc);
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
