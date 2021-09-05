import setting.Setting;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    private int width = 40;
    private int height = 40;

    public Player(int x, int y, Type type, Handler handler) {
        super(x, y, type);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        if (handler.isUp()) velY = -2;
        else if(!handler.isDown()) velY = 0;
        if (handler.isDown()) velY = 2;
        else if(!handler.isUp()) velY = 0;
        if (handler.isLeft()) velX = -2;
        else if(!handler.isRight()) velX = 0;
        if (handler.isRight()) velX = 2;
        else if(!handler.isLeft()) velX = 0;

        if (y > Game.HEIGHT-height) {
            y = Game.HEIGHT-height;
        }
        if(y < 0){
            y = 0;
        }

        if (x > Game.WIDTH-width) {
            x = Game.WIDTH-width;
        }
        if(x < 0){
            x = 0;
        }
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getType() == Type.Obstacle) {
                if (getBounds().intersects(object.getBounds())) {
                        x += velX * -1;
                        y += velY * -1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
