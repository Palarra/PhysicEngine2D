package engine.math;

public class Vector2f {

    public double x;
    public double y;

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double length = length();
        x /= length;
        y /= length;
    }

    public void multiply(Vector2f other) {
        x *= other.x;
        y *= other.y;
    }

    public void divide(Vector2f other) {
        x *= other.x;
        y *= other.y;
    }

    public void add(Vector2f other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vector2f other) {
        x -= other.x;
        y -= other.y;
    }
}
