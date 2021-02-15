package engine;

import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Engine2D {

    public static List<RigidBody2D> rb2dList = new ArrayList<>();
    public static List<Collider2D> c2dList = new ArrayList<>();
    static float t = 0;

    public static void updatePhysics(World world) {
        Vector2f newPosition;

        // Positional Logic
        for (RigidBody2D rb2d : rb2dList) {
            if (!rb2d.isStatic()) {
                rb2d.applyForce(new Vector2f(0, 0.05f));
                //rb2d.applyForce(new Vector2f(0.02f, 0f));
                rb2d.updateVelocity();

                newPosition = new Vector2f(rb2d.getParent().getPosition().x + rb2d.getVelocity().x, rb2d.getParent().getPosition().y + rb2d.getVelocity().y);
                rb2d.updatePosition(newPosition);
            }
        }

        checkCollisionStatic();


        Collider2D c1;
        Collider2D c2;

        for (int i = 0; i < c2dList.size(); i++) {
            c1 = c2dList.get(i);

            for (int j = i + 1; j < c2dList.size(); j++) {
                c2 = c2dList.get(j);

                if (c1.isColliding(c2) && !c1.getParent().getRb2d().isStatic() && !c2.getParent().getRb2d().isStatic()) {
                    Object2D obj1 = c1.getParent();
                    Object2D obj2 = c2.getParent();

                    Vector2f vCollision = new Vector2f(c2.getCenter().x - c1.getCenter().x, c2.getCenter().y - c1.getCenter().y);
                    float distance = (float) Math.sqrt((c2.getCenter().x - c1.getCenter().x) * (c2.getCenter().x - c1.getCenter().x) +
                            (c2.getCenter().y - c1.getCenter().y) * (c2.getCenter().y - c1.getCenter().y));

                    Vector2f vCollisionNorm = new Vector2f(vCollision.x / distance, vCollision.y / distance);
                    Vector2f vRelativeVelocity = new Vector2f(obj1.getRb2d().getVX() - obj2.getRb2d().getVX(), obj1.getRb2d().getVY() - obj2.getRb2d().getVY());
                    float speed = vRelativeVelocity.x * vCollisionNorm.x + vRelativeVelocity.y * vCollisionNorm.y;
                    //speed *= 0.8f;

                    if (speed < 0) {
                        break;
                    }

                    float impulse = 2 * speed / (obj1.getRb2d().getMass() + obj2.getRb2d().getMass());
                    float factor = 1;
                    if (!obj1.getRb2d().isStatic()) {
                        obj1.getRb2d().setVX(obj1.getRb2d().getVX() - (impulse * factor * obj2.getRb2d().getMass() * vCollisionNorm.x));
                        //obj1.getRb2d().setVY(obj1.getRb2d().getVY() - (impulse * factor * obj2.getRb2d().getMass() * vCollisionNorm.y));
                    }

                    if (!obj2.getRb2d().isStatic()) {
                        //obj2.getRb2d().setVX(obj1.getRb2d().getVX() + (impulse * factor * obj1.getRb2d().getMass() * vCollisionNorm.x));
                        obj2.getRb2d().setVY(obj1.getRb2d().getVY() + (impulse * factor * obj1.getRb2d().getMass() * vCollisionNorm.y));
                    }

                }


            }

        }
    }


    public static void checkCollisionStatic() {
        float restitution = 0.5f;

        for (Collider2D collider : c2dList) {
            if (collider.getParent().getRb2d().isStatic()) {
                for (Collider2D collider2 : c2dList) {
                    if (!collider2.getParent().getRb2d().isStatic() && collider.isColliding(collider2)) {
                        Object2D objStatic = collider.getParent();
                        Object2D obj = collider2.getParent();

                        /* Venant du dessus */
                        if (obj.getY() < objStatic.getY()) {
                            obj.getRb2d().setVY(-Math.abs(obj.getRb2d().getVY()) * restitution);
                            obj.setY(objStatic.getY() - obj.getSize().y);
                        }
                    }
                }
            }

        }
    }
}

