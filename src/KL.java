import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KL extends KeyAdapter {
    Handler handler;
    public KL(Handler handler) {
        this.handler = handler;
    }
    public boolean keys[] = new boolean[4];


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if (object.getType() == Type.Player) {
                if (key == KeyEvent.VK_W) { handler.setUp(true); }
                if (key == KeyEvent.VK_S) { handler.setDown(true); }
                if (key == KeyEvent.VK_A) { handler.setLeft(true); }
                if (key == KeyEvent.VK_D) { handler.setRight(true); }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if (object.getType() == Type.Player) {
                if (key == KeyEvent.VK_W) { handler.setUp(false); }
                if (key == KeyEvent.VK_S) { handler.setDown(false); }
                if (key == KeyEvent.VK_A) { handler.setLeft(false); }
                if (key == KeyEvent.VK_D) { handler.setRight(false); }
            }
        }
    }
}
