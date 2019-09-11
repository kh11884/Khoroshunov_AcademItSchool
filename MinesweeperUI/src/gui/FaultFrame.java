package gui;

import javax.swing.*;
import java.awt.*;

class FaultFrame {

    static void createFaultFrame() {
        JFrame faultFrame = new JFrame("Вы проиграли");
        faultFrame.setSize(300, 200);
        faultFrame.setResizable(false);
        faultFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        faultFrame.setIconImage(icon.getImage());
        faultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        faultFrame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(2, 1, 1, 1));
        faultFrame.add(panel);

        panel.add(new Label("Вы програли!"));

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            faultFrame.dispose();
            MineField.createMineField();
        });
        panel.add(exitButton);

    }
}
