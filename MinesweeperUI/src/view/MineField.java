package view;

import javax.swing.*;
import java.awt.*;

public class MineField {
    public static void createMineField() {
        JFrame frame = new JFrame("Сапер");
        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/view/image/icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(9, 9));
        frame.add(panel);
    }
}
