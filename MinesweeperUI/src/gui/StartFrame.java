package gui;

import javax.swing.*;
import java.awt.*;

public class StartFrame {
    public static void createStartFrame() {
        JFrame startFrame = new JFrame("Сапер.");
        startFrame.setSize(300, 480);
        startFrame.setResizable(false);
        startFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        startFrame.setIconImage(icon.getImage());
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startFrame.add(flow);

        JPanel panel = new JPanel(new GridLayout(7, 1, 1, 1));
        flow.add(panel);

        JLabel levelLabel = new JLabel("Выбери уровень игры:");
        panel.add(levelLabel);

        JButton beginnerButton = new JButton("<html><div align=\"center\">\"новичок\"<br>" +
                "размер поля 9х9<br>" +
                "Количество мин 10<div><html>");
        beginnerButton.addActionListener(e -> {
            startFrame.dispose();
            GameField.createMineField(9, 9, 10, "beginner");
        });
        panel.add(beginnerButton);

        JButton intermediateButton = new JButton("<html><div align=\"center\">\"опытный\"<br>" +
                "размер поля 16х16<br>" +
                "Количество мин 40<div><html>");
        intermediateButton.addActionListener(e -> {
            startFrame.dispose();
            GameField.createMineField(16, 16, 40, "intermediate");
        });
        panel.add(intermediateButton);

        JButton expertButton = new JButton("<html><div align=\"center\">\"эксперт\"<br>" +
                "размер поля 16х16<br>" +
                "Количество мин 99<div><html>");
        expertButton.addActionListener(e -> {
            startFrame.dispose();
            GameField.createMineField(16, 30, 99, "expert");
        });
        panel.add(expertButton);

        JLabel emptyLabel = new JLabel();
        panel.add(emptyLabel);

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> AboutPane.createAboutPane());
        panel.add(aboutButton);

        JButton exitButton = new JButton("выход");
        exitButton.addActionListener(e -> startFrame.dispose());
        panel.add(exitButton);
    }
}
