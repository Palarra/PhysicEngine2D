package graphic;

import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Quad extends Model {

    public Quad() {
        this(Color.RANDOM);
    }

    public Quad(Color color) {
        this.color = color;
    }

    @Override public void draw(Vector2f position, Vector2f size) {
        glColor4f(color.getR(), color.getG(), color.getB(), 1);
        glBegin(GL_LINE_LOOP);
        glVertex2f(position.x + 2, position.y + 2);
        glVertex2f(position.x + 2 + size.x - 4, position.y + 2);
        glVertex2f(position.x + 2 + size.x - 4, position.y + 2 + size.y - 4);
        glVertex2f(position.x + 2, position.y + 2 + size.y - 4);
        glEnd();
    }
}
