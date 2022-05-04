package redcoder.tank.stage.generator;

public abstract class StageGeneratorBase implements StageGenerator {

    protected String name;

    public StageGeneratorBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
