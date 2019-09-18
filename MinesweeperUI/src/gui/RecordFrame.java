package gui;

import javax.swing.*;
import java.awt.*;

 class RecordFrame {
    static void createRecordFrame() {
        MineField.timer.stop();
        MineField.frame.setEnabled(false);
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
                MineField.frame.setVisible(true);
                MineField.frame.setEnabled(true);
                MineField.timer.start();
            }
        });

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panel = new JPanel(new GridLayout(12, 1, 1, 1));
        recordFrame.add(flow);
        flow.add(panel);
        JLabel title = new JLabel("Таблица рекордов:" );
        panel.add(title);
        for (int i = 0; i < 10 ; i++) {
            String labelText;
            if(i < MineField.recordTable.getRecordTable().size()){
                labelText = i + 1 + " место - " + MineField.recordTable.getRecordTable().get(i) + " секунд";
            } else {
                labelText = "";
            }

            JLabel label = new JLabel(labelText);
            panel.add(label);
        }

        JButton exitButton = new JButton("Закрыть");
        exitButton.addActionListener(e -> {
            recordFrame.dispose();
            MineField.frame.setEnabled(true);
            MineField.frame.setVisible(true);
            MineField.timer.start();
        });
        panel.add(exitButton);
    }
}
