package gui;

import javax.swing.*;
import java.awt.*;


class WinFrame {

    static void createWinFrame(JFrame frame) {
        frame.setEnabled(false);
        JFrame faultFrame = new JFrame("Вы выйграли");
        faultFrame.setSize(300, 200);
        faultFrame.setResizable(false);
        //frame.setLocationRelativeTo(null);
        faultFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        faultFrame.setIconImage(icon.getImage());
        faultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        faultFrame.setVisible(true);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panel = new JPanel(new GridLayout(3, 1, 1, 1));
        faultFrame.add(flow);
        flow.add(panel);

        JLabel label = new JLabel("Вы выйграли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\win.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
        panel.add(label);
        panel.add(new Label());

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            frame.dispose();
            faultFrame.dispose();
            MineField.createMineField();
        });
        panel.add(exitButton);
    }
}