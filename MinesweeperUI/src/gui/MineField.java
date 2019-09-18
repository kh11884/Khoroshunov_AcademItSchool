package gui;

import model.MinesFieldTable;

import javax.swing.*;
import java.awt.*;

public class MineField {
    static JFrame frame;

    public static void createMineField() {
        int height = 9;
        int weight = 9;
        int minesQuantity = 10;

        frame = new JFrame("Сапер");
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

        JButton aboutButton = new JButton("About");
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

        ButtonsField buttonsField = new ButtonsField(weight, height);

        for (int y = 0; y < buttonsField.getHeight(); y++) {
            for (int x = 0; x < buttonsField.getWidth(); x++) {
                JButton button = buttonsField.getButton(x, y);
                panel.add(button);
            }
        }
    }

}
