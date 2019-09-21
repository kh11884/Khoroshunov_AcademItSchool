package gui;

import model.RecordTable;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameField {
    static JFrame frame;
    static Timer timer;
    public static RecordTable recordTable;
    static AtomicInteger timerValue;

    static void createMineField(int height, int weight, int minesQuantity, String level) {
        recordTable = new RecordTable(level);

        frame = new JFrame("Сапер");
        frame.setSize(45 * weight + 180, 46 * height);
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

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setPreferredSize(dimension);
        newGameButton.addActionListener(e -> {
            frame.dispose();
            timer.stop();
            StartFrame.createStartFrame();
        });
        leftPanel.add(newGameButton);

        JButton highScoresSButton = new JButton("Таблица рекордов");
        highScoresSButton.setPreferredSize(dimension);
        highScoresSButton.addActionListener(e -> RecordFrame.createRecordFrame());
        leftPanel.add(highScoresSButton);

        timerValue = new AtomicInteger(0);
        JLabel timeRest = new JLabel();
        timer = new Timer(100, e -> {
            timerValue.getAndIncrement();
            timeRest.setText("Время игры " + String.format("%02d:%02d.%01d", timerValue.get() / 600, timerValue.get() / 10 % 60, timerValue.get() % 10));
        });
        timer.start();
        leftPanel.add(timeRest);

        JLabel minesQuantityLabel = new JLabel("Количество мин: " + minesQuantity);
        leftPanel.add(minesQuantityLabel);

        ButtonsField buttonsField = new ButtonsField(weight, height, minesQuantity);

        JPanel panel = new JPanel(new GridLayout(height, weight, 1, 1));
        frame.add(panel);
        for (int y = 0; y < buttonsField.getHeight(); y++) {
            for (int x = 0; x < buttonsField.getWidth(); x++) {
                JButton button = buttonsField.getButton(x, y);
                panel.add(button);
            }
        }

        leftPanel.add(new Label());

        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener(e -> {
            timer.stop();
            frame.dispose();
        });
        leftPanel.add(exitButton);
    }
}
