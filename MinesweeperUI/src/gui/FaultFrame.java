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

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panel = new JPanel(new GridLayout(3, 1, 1, 1));
        faultFrame.add(flow);
        flow.add(panel);

        JLabel label = new JLabel("Вы програли!");
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\explosion.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label);

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            GameField.frame.dispose();
            faultFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton);
    }
}
