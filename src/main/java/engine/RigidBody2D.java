package engine;

import org.lwjgl.util.vector.Vector2f;

public class RigidBody2D {
    private Object2D parent;
    private float mass;
    private Vector2f velocity;
    private Vector2f acceleration;
    private boolean isStatic;


    public RigidBody2D() {
        this(1);
    }


    public RigidBody2D(float mass) {
        this.mass = mass;
        velocity = new Vector2f(0f, 0f);
        acceleration = new Vector2f(0f, 0f);
        isStatic = false;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public float getVX() {
        return velocity.x;
    }

    public float getVY() {
        return velocity.y;
    }

    public void setVX(float vx) {
        setVelocity(new Vector2f(vx, velocity.y));
    }

    public void setVY(float vy) {
        setVelocity(new Vector2f(velocity.x, vy));
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public Object2D getParent() {
        return parent;
    }

    public void setParent(Object2D parent) {
        this.parent = parent;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void applyForce(Vector2f force) {
        Vector2f.add(acceleration, force, acceleration);
    }

    public void updateVelocity() {
        Vector2f.add(velocity, acceleration, velocity);
        acceleration.x = 0;
        acceleration.y = 0;
    }

    public void updatePosition(Vector2f newPosition) {
        parent.setPosition(newPosition);
    }
}
