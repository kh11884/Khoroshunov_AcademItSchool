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

        GridBagConstraints cell4 = new GridBagConstraints();
        cell4.gridx = 0;
        cell4.gridy = 3;
        cell4.insets = new Insets(3, 5, 5, 5);

        JLabel label = new JLabel("Вы выйграли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\win.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label, cell1);

        panel.add(new Label(), cell2);

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            winFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton, cell3);
    }
}