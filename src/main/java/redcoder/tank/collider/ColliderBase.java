package redcoder.tank.collider;

public abstract class ColliderBase implements Collider {

    protected String name;

    public ColliderBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
