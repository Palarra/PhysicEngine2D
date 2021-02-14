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
        glColor4f(0, 0, 0, color.getA());
        glBegin(GL_QUADS);
        glVertex2f(position.x, position.y);
        glVertex2f(position.x + size.x, position.y);
        glVertex2f(position.x + size.x, position.y + size.y);
        glVertex2f(position.x, position.y + size.y);

        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glBegin(GL_QUADS);
        glVertex2f(position.x + 2, position.y + 2);
        glVertex2f(position.x  + 2 + size.x - 4, position.y + 2);
        glVertex2f(position.x + 2 + size.x - 4, position.y + 2 + size.y - 4);
        glVertex2f(position.x + 2, position.y + 2 + size.y - 4);



        glEnd();
    }
}
