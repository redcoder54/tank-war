package redcoder.tank.collider;

import redcoder.tank.gameobj.GameObj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 包含多个碰撞器的执行链。
 */
public class ColliderChain extends ColliderBase {

    private Set<String> colliderNames = new HashSet<>();
    private List<Collider> colliders = new ArrayList<>();

    public ColliderChain(String name) {
        super(name);
    }

    @Override
    public boolean collide(GameObj o1, GameObj o2) {
        for (Collider collider : colliders) {
            if (!collider.collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }

    public void addCollider(Collider collider) {
        if (colliderNames.add(collider.getName())) {
            this.colliders.add(collider);
        }
    }
}
