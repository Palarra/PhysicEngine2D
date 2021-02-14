package graphic;

import org.lwjgl.util.vector.Vector2f;

abstract public class Model {
    protected Color color;
    public abstract void draw(Vector2f position, Vector2f size);
}
