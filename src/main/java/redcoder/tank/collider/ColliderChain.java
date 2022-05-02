package redcoder.tank.collider;

import redcoder.tank.gameobj.GameObj;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含多个碰撞器的执行链。
 */
public class ColliderChain implements Collider{

    private List<Collider> colliders = new ArrayList<>();

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
        colliders.add(collider);
    }
}
