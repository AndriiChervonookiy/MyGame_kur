import setting.Setting;

import javax.swing.*;

public class Camera {
    private int x, y, width, height;
    private  Handler handler;
    private GameObject player = null;

    public Camera(int x, int y, int width, int height, Handler handler) {
        this.x = x;
        this.y = y;
        //
        this.width = width;
        this.height = height;
        this.handler = handler;

        findPlayer();
    }

    public void findPlayer() {
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getType() == Type.Player) {
                player = handler.objects.get(i);
                break;
            }
        }
    }

    public void tick(GameObject object) {
        x += ((object.getX() - x) - width/2) * 0.08f;
        y += ((object.getY() - y) - height/2) * 0.08f;

        if (x <= 0) x = 0;
        if (x+width >= 28*50) x = 28*50-width;
        if (y <= 0) y = 0;
        if (y+height >= 28*50+50) y = 28*50-height+50;

        //if (player != null) {
        //    x = (int)player.x - width/2-25;
        //    y = (int)player.y - height/2-25;
        //}
        //else {
        //    findPlayer();
        //}
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
