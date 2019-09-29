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

        GridBagConstraints cell1 = new GridBagConstraints();
        cell1.gridx = 0;
        cell1.gridy = 0;
        cell1.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell2 = new GridBagConstraints();
        cell2.gridx = 0;
        cell2.gridy = 1;
        cell2.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell3 = new GridBagConstraints();
        cell3.gridx = 0;
        cell3.gridy = 2;
        cell3.insets = new Insets(3, 5, 5, 5);

        JLabel label = new JLabel("Вы програли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\explosion.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label, cell1);

        panel.add(new Label(), cell2);

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            faultFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton, cell3);
    }
}
