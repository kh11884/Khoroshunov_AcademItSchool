package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
        private static void createStartFrame() {
            JFrame startFrame = new JFrame("Сапер.");
            startFrame.setSize(300, 420);
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


            AtomicInteger secondRest = new AtomicInteger(0);
            JLabel timeRest = new JLabel();
           Timer timer = new Timer(100, e -> {
                secondRest.getAndIncrement();
                timeRest.setText("Время игры " + String.format("%02d:%02d.%01d", secondRest.get() / 600, secondRest.get()/10 % 60, secondRest.get()%10));
            });
            timer.start();
            panel.add(timeRest);


            JLabel levelLabel = new JLabel("Выбери уровень игры:");
            panel.add(levelLabel);




            JButton exitButton = new JButton("выход");
            exitButton.addActionListener(e -> {
                startFrame.dispose();
            });
            panel.add(exitButton);
        }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::createStartFrame);
    }
}
