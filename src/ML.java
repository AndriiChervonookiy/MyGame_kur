import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ML extends MouseAdapter {

    private Handler handler;
    private Camera camera;

    public ML(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX() + camera.getX();
        int y = e.getY() + camera.getY();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getType() == Type.Player) {
                GameObject bullet = new Bullet((int)object.getX()+10, (int)object.getY()+10, Type.Bullet, handler, x-20, y);
                handler.addObject(bullet);
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }
}