import setting.Setting;

import javax.swing.*;
import java.awt.*;

public class Window {
    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();


        frame.setSize((int)width,(int)height);
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //frame.setMinimumSize(new Dimension(width, height));
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }
}
