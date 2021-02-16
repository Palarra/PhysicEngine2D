import static org.lwjgl.opengl.GL11.*;


import engine.collision.BoxCollider2D;
import engine.collision.Collider2D;
import engine.Object2D;
import engine.RigidBody2D;
import engine.World;
import graphic.Quad;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

public class Main {

    private int width;
    private int height;
    private int time = 0;

    World world;

    public Main(String title, int width, int height) {
        this.width = width;
        this.height = height;

        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.setResizable(false);
            Display.create();
            glClearColor(0, 0, 0, 1.0f);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        } catch (final LWJGLException e) {
            e.printStackTrace();
        }
        world = new World();
    }

    public void start() {
        while (!Display.isCloseRequested()) {
            Display.sync(60);
            update();
            render();
            Display.update();
        }
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
        glViewport(0, 0, width, height);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        world.render();
    }

    public void update() {
        if (time % 10 == 0){
            Object2D box = new Object2D(new Quad());
            box.setPosition(new Vector2f(10, 10));
            box.setSize(new Vector2f(25, 25));
            box.addRigidBody2D(new RigidBody2D(1, 0.5f));
            box.addCollider2D(new BoxCollider2D());
        }
        time++;
        world.update();
    }

    public static void main(String[] args) {
        Main m = new Main("Physics2D", 1000, 1000);

        for (int i = 0; i < 2; i++) {
            Object2D box = new Object2D(new Quad());
            box.setPosition(new Vector2f((float) (i * (50 + Math.random() * 10))% 800,(float)Math.random() * 500));
            box.setSize(new Vector2f(25, 25));
            box.addRigidBody2D(new RigidBody2D(1, 0.5f));
            box.addCollider2D(new BoxCollider2D());
            //box.getRb2d().setVX((float) ((Math.random() * 2 - 1) ));
            //box.getRb2d().setVY((float) ((Math.random() * 2 - 1) ));
        }


        Object2D ground = new Object2D(new Quad());
        ground.setPosition(new Vector2f(0,900));
        ground.setSize(new Vector2f(800, 200));
        ground.addRigidBody2D(new RigidBody2D(100, 0.5f));
        ground.getRb2d().setStatic(true);
        ground.addCollider2D(new BoxCollider2D());

        Object2D wall = new Object2D(new Quad());
        wall.setPosition(new Vector2f(800,0));
        wall.setSize(new Vector2f(100, 1500));
        wall.addRigidBody2D(new RigidBody2D(100, 0.5f));
        wall.getRb2d().setStatic(true);
        wall.addCollider2D(new BoxCollider2D());




        /*Object2D box1 = new Object2D(new Quad(Color.GREEN));
        box1.setPosition(new Vector2f(425, 360));
        box1.setSize(new Vector2f(50, 50));
        box1.addRigidBody2D(new RigidBody2D());
        box1.addCollider2D(new Collider2D());

        Object2D box4 = new Object2D(new Quad(Color.BLACK));
        box4.setPosition(new Vector2f(425, 100));
        box4.setSize(new Vector2f(50, 50));
        box4.addRigidBody2D(new RigidBody2D(5));
        box4.addCollider2D(new Collider2D());*/

        /*m.render();
        Display.update();*/
        m.start();
    }
}

