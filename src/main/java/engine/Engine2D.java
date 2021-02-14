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
                rb2d.applyForce(new Vector2f(0, 0.5f));
                //rb2d.applyForce(new Vector2f(0.02f, 0f));
                rb2d.updateVelocity();

                newPosition = new Vector2f(rb2d.getParent().getPosition().x + rb2d.getVelocity().x, rb2d.getParent().getPosition().y + rb2d.getVelocity().y);
                rb2d.updatePosition(newPosition);
            }
        }

        // Collision Detection Static
        for (Collider2D obj1 : c2dList) {
            if (obj1.getParent().getRb2d().isStatic()) {
                for (Collider2D obj2 : c2dList) {
                    if (obj1 != obj2 && obj1.isColliding(obj2)) {
                        resolveCollision(obj2.getParent(), obj1.getParent());

                        test(obj2.getParent());
                    }
                }
            }
        }

        // Collision Detection Non Static
        for (Collider2D obj1 : c2dList) {
            if (!obj1.getParent().getRb2d().isStatic()) {
                for (Collider2D obj2 : c2dList) {
                    if (obj1 != obj2 && !obj2.getParent().getRb2d().isStatic() && obj1.isColliding(obj2)) {
                        resolveCollision(obj1.getParent(), obj2.getParent());
                        test(obj1.getParent());
                    }
                }
            }

        }
    }


    private static void test(Object2D obj1) {

        boolean isColliding = false;

        Collider2D c1 = obj1.getC2d();
        RigidBody2D r1 = obj1.getRb2d();

        for (Collider2D c2 : c2dList) {
            if (c1 != c2 && !c2.getParent().getRb2d().isStatic() && c1.isColliding(c2)) {
                resolveCollision(c2.getParent(), obj1);
                test(c2.getParent());
            }
        }
    }


    // Collision Resolution
    private static void resolveCollision(Object2D o1, Object2D o2) {

        Collider2D c1 = o1.getC2d();
        Collider2D c2 = o2.getC2d();

        float restitution = 0.25f;
        float treshold = 0.8f;

        float dx = (c2.getCenter().x - c1.getCenter().x) / c2.getSize().x / 2;
        float dy = (c2.getCenter().y - c1.getCenter().y) / c2.getSize().y / 2;

        float absDX = Math.abs(dx);
        float absDY = Math.abs(dy);

        //System.out.println("Vitesse :" + o1.getRb2d().getVelocity());


       /* if (Math.abs(absDX - absDY) < 0.1f) {
            if (dx < 0) {
                // Set the player x to the right side
                o1.setX(o2.getX() + c2.getSize().x);

                // If the player is approaching from negative X
            } else {

                // Set the player x to the left side
                o1.setX(o2.getX() - c1.getSize().x);
            }

            // If the player is approaching from positive Y
            if (dy < 0) {

                // Set the player y to the bottom
                o1.setY(o2.getY() + c2.getSize().y);
            } else {
                o1.setY(o2.getY() - c1.getSize().y);
            }

            if (Math.random() < 0.5f) {
                o1.getRb2d().setVX(-o1.getRb2d().getVX() * restitution);

                if (Math.abs(o1.getRb2d().getVX()) < treshold) {
                    o1.getRb2d().setVX(0.0f);
                }
            } else {
                o1.getRb2d().setVY(-o1.getRb2d().getVY() * restitution);
                if (Math.abs(o1.getRb2d().getVY()) < treshold) {
                    o1.getRb2d().setVY(0.0f);
                }
            }
        } else */if (absDX > absDY) {

            // Si arrive de gauche
            if (dx < 0) {
                o1.setX(o2.getX() + c2.getSize().x);

            } else {
                // Si arrive de droite
                o1.setX(o2.getX() - c1.getSize().x);
            }

            // Velocity component
            o1.getRb2d().setVX(-o1.getRb2d().getVX() * restitution);

            if (Math.abs(o1.getRb2d().getVX()) < treshold) {
                o1.getRb2d().setVX(0.0f);
            }

            // Collision haut et bas
        } else {

            // Si arrive du haut
            if (dy < 0) {
                o1.setY(o2.getY() + c2.getSize().y);

            } else {
                // Si arrive du bas
                o1.setY(o2.getY() - c1.getSize().y);
            }

            // Velocity component
            o1.getRb2d().setVY(-o1.getRb2d().getVY() * restitution);
            if (Math.abs(o1.getRb2d().getVY()) < treshold) {
                o1.getRb2d().setVY(0.0f);
                System.out.println("Reset de la vitesse");
            }
        }
    }
}

