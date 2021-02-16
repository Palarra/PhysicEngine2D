package engine.collision;

public class BoxCollider2D extends Collider2D{
    @Override
    public boolean isColliding(Collider2D other) {
        float l1 = parent.getX();
        float t1 = parent.getY();
        float r1 = parent.getX() + size.x;
        float b1 = parent.getY() + size.y;

        float l2 = other.parent.getX();
        float t2 = other.parent.getY();
        float r2 = other.parent.getX() + other.size.x;
        float b2 = other.parent.getY() + other.size.y;

        // If the any of the edges are beyond any of the
        // others, then we know that the box cannot be
        // colliding
        if (b1 <= t2 || t1 >= b2 || r1 <= l2 || l1 >= r2) {
            return false;
        }

        // If the algorithm made it here, it had to collide
        return true;
    }
}
