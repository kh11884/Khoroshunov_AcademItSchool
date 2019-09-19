package gui;

import javax.swing.*;
import java.awt.*;

class RecordFrame {
    static void createRecordFrame() {
        GameField.timer.stop();
        GameField.frame.setEnabled(false);
        JFrame recordFrame = new JFrame("Таблица рекордов");
        recordFrame.setSize(200, 400);
        recordFrame.setResizable(false);
        recordFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        recordFrame.setIconImage(icon.getImage());
        recordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recordFrame.setVisible(true);

        recordFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                GameField.frame.setVisible(true);
                GameField.frame.setEnabled(true);
                GameField.timer.start();
            }
        });

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panel = new JPanel(new GridLayout(12, 1, 1, 1));
        recordFrame.add(flow);
        flow.add(panel);
        JLabel title = new JLabel("Таблица рекордов:");
        panel.add(title);
        for (int i = 0; i < 10; i++) {
            String labelText;
            if (i < GameField.recordTable.getRecordTable().size()) {
                int recordTime = GameField.recordTable.getRecordTable().get(i);
                labelText = i + 1 + " место - " + String.format("%02d:%02d.%01d", recordTime / 600, recordTime / 10 % 60, recordTime % 10);
            } else {
                labelText = "";
            }

            JLabel label = new JLabel(labelText);
            panel.add(label);
        }

        JButton exitButton = new JButton("Закрыть");
        exitButton.addActionListener(e -> {
            recordFrame.dispose();
            GameField.frame.setEnabled(true);
            GameField.frame.setVisible(true);
            GameField.timer.start();
        });
        panel.add(exitButton);
    }
}
