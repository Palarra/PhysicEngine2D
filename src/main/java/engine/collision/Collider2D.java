package engine.collision;

import engine.Object2D;
import org.lwjgl.util.vector.Vector2f;

public abstract class Collider2D {
    protected Vector2f size;
    protected Vector2f center;
    protected Object2D parent;

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

    public abstract boolean isColliding(Collider2D other);
}
