package engine;

import engine.collision.BoxCollider2D;
import engine.collision.Collider2D;
import graphic.Color;
import graphic.Quad;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static final float EARTH_GRAVITY = 9.81f;
    private float gravity;

    public static List<Object2D> objects = new ArrayList<>();

    public World () {

    }

    public void update() {
        Engine2D.updatePhysics(this);
        if (Mouse.isButtonDown(0)) {
            Object2D box3 = new Object2D(new Quad(Color.RED));
            box3.setPosition(new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY()));
            box3.setSize(new Vector2f(20, 20));
            box3.addRigidBody2D(new RigidBody2D());
            box3.addCollider2D(new BoxCollider2D());
        }
    }

    public void render() {
        for (Object2D o : objects) {
            o.render();
        }
    }
}
