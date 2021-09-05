import setting.Setting;

import java.awt.*;

public class Obstacle extends GameObject {

    public Obstacle(int x, int y, Type type) {
        super(x, y, type);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, Setting.TILE_WIDTH, Setting.TILE_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 50, 50);
    }
}
