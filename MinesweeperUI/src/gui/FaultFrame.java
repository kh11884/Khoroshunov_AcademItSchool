package gui;

import javax.swing.*;
import java.awt.*;

class FaultFrame {

    static void createFaultFrame() {
        GameField.timer.stop();
        GameField.frame.setEnabled(false);
        JFrame faultFrame = new JFrame("Вы проиграли");
        faultFrame.setSize(300, 200);
        faultFrame.setResizable(false);
        faultFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        faultFrame.setIconImage(icon.getImage());
        faultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        faultFrame.setVisible(true);
        faultFrame.setAlwaysOnTop(true);

        faultFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                GameField.frame.setVisible(true);
                GameField.frame.setEnabled(true);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        faultFrame.add(panel);

        GridBagConstraints cell_1 = new GridBagConstraints();
        cell_1.gridx = 0;
        cell_1.gridy = 0;
        cell_1.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell_2 = new GridBagConstraints();
        cell_2.gridx = 0;
        cell_2.gridy = 1;
        cell_2.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell_3 = new GridBagConstraints();
        cell_3.gridx = 0;
        cell_3.gridy = 2;
        cell_3.insets = new Insets(3, 5, 5, 5);

        JLabel label = new JLabel("Вы програли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\explosion.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label, cell_1);

        panel.add(new Label(), cell_2);

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            faultFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton, cell_3);
    }
}
