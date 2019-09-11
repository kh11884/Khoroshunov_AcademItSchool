package gui;

import model.MinesFieldTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineField {

    public static void createMineField() {
        int height = 9;
        int weight = 9;
        int minesQuantity = 10;

        JFrame frame = new JFrame("Сапер");
        frame.setSize(640, 480);
        //frame.setResizable(false);
        // frame.setLocationRelativeTo(null);
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


        ImageIcon bomb = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Khoroshunov_AcademItSchool_new\\MinesweeperUI\\src\\resources\\mines_icon.jpg");

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


//                    if (buttonText.equals("9")) {
//                        button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
//
//                    } else {
//                        button.setText(buttonText);
//                    }

                ImageIcon imageFlag = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Khoroshunov_AcademItSchool_new\\MinesweeperUI\\src\\resources\\flag.jpg");
                Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (button.getIcon() == null) {
                                button.setIcon(flag);
                            } else {
                                button.setIcon(null);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                int row = i;
                int column = j;
                button.addActionListener(e -> {
                    button.setContentAreaFilled(false);
                    if (buttonText.equals("9")) {
                        button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
                        FaultFrame.createFaultFrame();
                        frame.dispose();
                    } else {
                        button.setText(buttonText);
                        System.out.println("ряд - " + (row + 1) + ", колонка - " + (column + 1));
                    }
                });
                panel.add(button);
            }
        }
    }
}
