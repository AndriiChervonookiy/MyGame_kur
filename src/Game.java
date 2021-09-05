import setting.Setting;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    static int WIDTH = 28*Setting.TILE_WIDTH;
    static int HEIGHT = 28*Setting.TILE_HEIGHT;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public double width = screenSize.getWidth();
    public double height = screenSize.getHeight();

    private Thread thread;
    private boolean isRunning = false;

    //instances
    private Handler handler;
    private ML ml;
    private Camera camera;

    private BufferedImage location = null;

    public Game() {
        new Window(Setting.GAME_TITLE, this);
        start();
        init();
    }

    private void init() {
        handler = new Handler();
        camera = new Camera(0, 0, (int)width, (int)height, handler);

        this.addKeyListener(new KL(handler));
        this.addMouseListener(new ML(handler, camera));

        BufferedImageLoader loader = new BufferedImageLoader();
        location = loader.loadImage("/testmap.png");

        loadLevel(location);
    }

    private synchronized void start() {
        if (isRunning) return;

        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop() {
        if (!isRunning) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

    //game loop
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        for (int i = 0; i < handler.objects.size(); i++){
            if (handler.objects.get(i).getType() == Type.Player){
                camera.tick(handler.objects.get(i));
            }
        }
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;
        //
        g.setColor(Color.black);
        g.fillRect(0, 0, (int)width, (int)height);

        g2.translate(-camera.getX(), -camera.getY());
        handler.render(g);
        g2.translate(camera.getX(), camera.getY());
        //
        g.dispose();
        bs.show();
    }

    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject(new Obstacle(x * Setting.TILE_WIDTH, y * Setting.TILE_HEIGHT, Type.Obstacle));
                }
                if (blue == 255) {
                    handler.addObject(new Player(x * Setting.TILE_WIDTH, y * Setting.TILE_HEIGHT, Type.Player, handler));
                }
                if (green == 255) {
                    handler.addObject(new Enemy(x * Setting.TILE_WIDTH, y * Setting.TILE_HEIGHT, Type.Enemy, handler));
                }
            }
        }
    }
}
