package gui;

import model.MinesFieldTable;

import javax.swing.*;
import java.awt.*;

public class MineField {

    public static void createMineField() {
        int height = 9;
        int weight = 9;
        int minesQuantity = 10;

        JFrame frame = new JFrame("Сапер");
        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Dimension dimension = new Dimension(200, 25);

        JPanel leftPanel = new JPanel();
        frame.add(leftPanel, BorderLayout.LINE_START);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton newGameButton = new JButton("новая игра");
        newGameButton.setPreferredSize(dimension);
        newGameButton.addActionListener(e -> {
            frame.dispose();
            MineField.createMineField();
        });
        leftPanel.add(newGameButton);

        JButton aboutButton = new JButton("около");
        aboutButton.setPreferredSize(dimension);
        leftPanel.add(aboutButton);

        JButton highScoresSButton = new JButton("Таблица рекордов");
        highScoresSButton.setPreferredSize(dimension);
        leftPanel.add(highScoresSButton);

        leftPanel.add(new Label());

        JButton exitButton = new JButton("выход");
        exitButton.addActionListener(e -> frame.dispose());
        leftPanel.add(exitButton);

        JPanel panel = new JPanel(new GridLayout(height, weight, 1, 1));
        frame.add(panel);

        MinesFieldTable minesFieldTable = new MinesFieldTable(minesQuantity, weight, height);

        ButtonsField buttonsField = new ButtonsField(weight, height);

        for (int i = 0; i < buttonsField.getHeigth(); i++) {
            for (int j = 0; j < buttonsField.getWidth(); j++) {
                JButton button = buttonsField.getButton(j, i);

                String buttonText;
                int buttonValue = minesFieldTable.getCellValue(j, i);
                if (buttonValue == 0) {
                    buttonText = "";
                } else {
                    buttonText = String.valueOf(buttonValue);
                }

                button.addMouseListener(new FieldButtonClick(frame, buttonsField, button, j, i, buttonText));
                panel.add(button);
            }
        }
    }
}
