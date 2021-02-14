package engine;

import graphic.Model;
import org.lwjgl.util.vector.Vector2f;

public class Object2D {
    protected Vector2f position;
    protected Vector2f rotation;
    protected Vector2f size;
    private Model model;
    private RigidBody2D rb2d;
    private Collider2D c2d;

    public Object2D(Model model) {
        this.model = model;
        World.objects.add(this);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
            this.position = position;
    }

    public void setX(float x) {
        setPosition(new Vector2f(x, position.y));
    }

    public void setY(float y) {
        setPosition(new Vector2f(position.x, y));
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public RigidBody2D getRb2d() {
        return rb2d;
    }

    public void addRigidBody2D(RigidBody2D rb2d) {
        this.rb2d = rb2d;
        rb2d.setParent(this);
        Engine2D.rb2dList.add(rb2d);
    }

    public Collider2D getC2d() {
        return c2d;
    }

    public void addCollider2D(Collider2D c2d) {
        this.c2d = c2d;
        c2d.setParent(this);
        Engine2D.c2dList.add(c2d);
    }

    public void render() {
        model.draw(position, size);
    }
}
