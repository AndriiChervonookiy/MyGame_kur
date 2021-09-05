import java.awt.*;

public class Bullet extends GameObject {
    private Handler handler;

    public Bullet(float x, float y, Type type, Handler handler, float mx, float my) {
        super(x, y, type);
        this.handler = handler;
        float angle = (float) Math.atan2(my - y, mx - x);
        int bulletVel = 10;

        velX = (float)((bulletVel) * Math.cos(angle));
        velY = (float)((bulletVel) * Math.sin(angle));
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getType() == Type.Obstacle) {
                if (getBounds().intersects(object.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval((int)x, (int)y, 5, 5);
    }

    @Override
    protected Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 5, 5);
    }
}
