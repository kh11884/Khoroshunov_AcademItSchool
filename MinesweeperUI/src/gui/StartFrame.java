package gui;

import javax.swing.*;
import java.awt.*;

public class StartFrame {
    public static void createStartFrame(){
        JFrame startFrame = new JFrame("Сапер.");
        startFrame.setSize(300, 300);;
        startFrame.setResizable(false);
        startFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        startFrame.setIconImage(icon.getImage());
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startFrame.add(flow);

        JPanel panel = new JPanel(new GridLayout(4, 1, 1, 1));
        flow.add(panel);

        JButton beginnerButton = new JButton("новичок");
        //newGameButton.setPreferredSize(dimension);
        beginnerButton.addActionListener(e -> {
            startFrame.dispose();
            MineField.createMineField(9, 9, 10, "beginner");
        });
        panel.add(beginnerButton);

        JButton intermediateButton = new JButton("опытный");
        //newGameButton.setPreferredSize(dimension);
        intermediateButton.addActionListener(e -> {
            startFrame.dispose();
            MineField.createMineField(16, 16, 40, "intermediate");
        });
        panel.add(intermediateButton);

        JButton expertButton = new JButton("эксперт");
        //newGameButton.setPreferredSize(dimension);
        expertButton.addActionListener(e -> {
            startFrame.dispose();
            MineField.createMineField(16, 30, 99, "expert");
        });

        panel.add(expertButton);
    }
}
