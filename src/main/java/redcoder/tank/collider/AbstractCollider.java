package redcoder.tank.collider;

public abstract class AbstractCollider implements Collider {

    protected String name;

    public AbstractCollider(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
