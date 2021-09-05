import setting.Setting;

import java.awt.*;

public class Enemy extends GameObject{

    private Handler handler;

    public Enemy(float x, float y, Type type, Handler handler) {
        super(x, y, type);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, Setting.TILE_WIDTH, Setting.TILE_HEIGHT);
    }

    @Override
    protected Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, Setting.TILE_WIDTH, Setting.TILE_HEIGHT);
    }
}
