package gui;

import javax.swing.*;
import java.awt.*;


class WinFrame {

    static void createWinFrame() {
        GameField.timer.stop();
        GameField.frame.setEnabled(false);
        JFrame winFrame = new JFrame("Вы выйграли");
        winFrame.setSize(300, 200);
        winFrame.setResizable(false);
        winFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        winFrame.setIconImage(icon.getImage());
        winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        winFrame.setVisible(true);

        winFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                GameField.frame.setVisible(true);
                GameField.frame.setEnabled(true);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        winFrame.add(panel);

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

        GridBagConstraints cell_4 = new GridBagConstraints();
        cell_4.gridx = 0;
        cell_4.gridy = 3;
        cell_4.insets = new Insets(3, 5, 5, 5);

        JLabel label = new JLabel("Вы выйграли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\win.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label, cell_1);

        panel.add(new Label(), cell_2);

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            winFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton, cell_3);
    }
}