package graphic;

public class Color {

    public static Color RANDOM = new Color().newInstance();

    /**
     * Ease-of-use enum for white color.
     */
    public static Color WHITE = new Color(1f, 1f, 1f, 1f);

    /**
     * Ease-of-use enum for black color.
     */
    public static Color BLACK = new Color(0.1f, 0.1f, 0.1f, 1f);

    /**
     * Ease-of-use enum for green color.
     */
    public static Color GREEN = new Color(0.2f, 0.7f, 0.2f, 1f);

    /**
     * Ease-of-use enum for red color.
     */
    public static Color RED = new Color(0.7f, 0.1f, 0.1f, 1f);

    /**
     * Ease-of-use enum for red color.
     */
    public static Color BLUE = new Color(0.2f, 0.2f, 0.7f, 1f);

    /**
     * Ease-of-use enum for gray color.
     */
    public static Color GRAY = new Color(0.4f, 0.4f, 0.4f, 1f);

    /**
     * Ease-of-use enum for gray color.
     */
    public static Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f, 1f);

    /**
     * Ease-of-use enum for gray color.
     */
    public static Color MIDDLE_GRAY = new Color(0.5f, 0.5f, 0.5f, 1f);

    /**
     * Ease-of-use enum for light-gray color.
     */
    public static Color LIGHT_GRAY = new Color(0.6f, 0.6f, 0.6f, 1.0f);

    /**
     * Ease-of-use enum for transparent color.
     */
    public static Color TRANSPARENT = new Color(1f, 1f, 1f, 0f);

    private final float r, g, b, a;

    /**
     * Construct a new color.
     *
     * @param r the red component of the color.
     * @param g the green component of the color.
     * @param b the blue component of the color.
     * @param a the alpha component of the color.
     */
    private Color(final float r, final float g, final float b, final float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Construct a new random color.
     */
    private Color() {
        this.r = (float) Math.random();
        this.g = (float) Math.random();
        this.b = (float) Math.random();
        this.a = 1;
    }

    /**
     * @return the red component of the color.
     */
    public float getR() {
        return r;
    }

    /**
     * @return the green component of the color.
     */
    public float getG() {
        return g;
    }

    /**
     * @return the blue component of the color.
     */
    public float getB() {
        return b;
    }

    /**
     * @return the alpha component of the color.
     */
    public float getA() {
        return a;
    }

    public Color newInstance() {
        return new Color();
    }
}
