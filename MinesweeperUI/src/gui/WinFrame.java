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

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panel = new JPanel(new GridLayout(3, 1, 1, 1));
        winFrame.add(flow);
        flow.add(panel);

        JLabel label = new JLabel("Вы выйграли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\win.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
        panel.add(label);
        panel.add(new Label());



        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            winFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton);
    }
}