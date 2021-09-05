import java.awt.*;

public abstract class GameObject {
    protected float x, y;
    protected float velX = 0, velY = 0;
    protected Type type;

    public GameObject(float x, float y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    protected abstract Rectangle getBounds();


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
