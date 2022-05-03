package redcoder.tank.stage;

public abstract class AbstractStageGenerator implements StageGenerator {

    protected String name;

    public AbstractStageGenerator(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
