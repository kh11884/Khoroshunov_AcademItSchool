package gui;

import javax.swing.*;
import java.awt.*;


class WinFrame {

    static void createWinFrame() {
        MineField.timer.stop();
        MineField.frame.setEnabled(false);
        JFrame winFrame = new JFrame("Вы выйграли");
        winFrame.setSize(300, 200);
        winFrame.setResizable(false);
        //frame.setLocationRelativeTo(null);
        winFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        winFrame.setIconImage(icon.getImage());
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(true);

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
            MineField.frame.dispose();
            winFrame.dispose();
            MineField.createMineField();
        });
        panel.add(exitButton);

    }
}