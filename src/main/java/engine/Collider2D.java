package engine;

import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

public class Collider2D {
    private Vector2f size;
    private Vector2f center;
    private Object2D parent;

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public Vector2f getCenter() {
        return new Vector2f(parent.getPosition().x + parent.getSize().x / 2, parent.getPosition().y + parent.getSize().y / 2);
    }

    public void setCenter(Vector2f center) {
        this.center = center;
    }

    public Object2D getParent() {
        return parent;
    }

    public void setParent(Object2D parent) {
        this.parent = parent;
        size = parent.getSize();
        center = new Vector2f(parent.getPosition().x + parent.getSize().x / 2, parent.getPosition().y + parent.getSize().y / 2);
    }

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
