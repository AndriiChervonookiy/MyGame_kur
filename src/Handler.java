import setting.Setting;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private boolean up = false, down = false, left = false, right = false;

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            object.tick();

            if (object.x < 0- Setting.TILE_WIDTH || object.x > 28*50+Setting.TILE_WIDTH ||
                    object.y < 0- Setting.TILE_HEIGHT || object.y > 28*50+Setting.TILE_HEIGHT) {
                this.removeObject(object);
            }
        }

    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            object.render(g);
        }
    }

    public GameObject addObject(GameObject object) {
        objects.add(object);
        return object;
    }

    public GameObject removeObject(GameObject object) {
        objects.remove(object);
        return object;
    }


    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
