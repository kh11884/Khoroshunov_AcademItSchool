package gui;

import model.RecordTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        frame.setSize(35 * weight, 35 * height + 70);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        GameMenuBar menuBar = new GameMenuBar();
        frame.setJMenuBar(menuBar.getJMenu());

        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(1, 2, 0, 0));
        topPanel.setPreferredSize(new Dimension(50, 30));

        JLabel minesQuantityLabel = new JLabel("Количество мин: " + minesQuantity);
        minesQuantityLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        topPanel.add(minesQuantityLabel);

        timerValue = new AtomicInteger(0);
        JLabel timeRest = new JLabel();
        timer = new Timer(100, e -> {
            timerValue.getAndIncrement();
            timeRest.setText(String.format("%02d:%02d.%01d", timerValue.get() / 600, timerValue.get() / 10 % 60, timerValue.get() % 10) + " :Время игры");
        });
        timer.start();
        timeRest.setHorizontalAlignment(SwingConstants.RIGHT);
        timeRest.setBorder(new EmptyBorder(0, 0, 0, 5));
        topPanel.add(timeRest);

        ButtonsField buttonsField = new ButtonsField(weight, height, minesQuantity);

        JPanel panel = new JPanel(new GridLayout(height, weight, 1, 1));
        frame.add(panel);
        for (int y = 0; y < buttonsField.getHeight(); y++) {
            for (int x = 0; x < buttonsField.getWidth(); x++) {
                JButton button = buttonsField.getButton(x, y);
                panel.add(button);
            }
        }
    }
}
