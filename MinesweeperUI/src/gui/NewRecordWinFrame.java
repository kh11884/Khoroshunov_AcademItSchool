package gui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Objects;


class NewRecordWinFrame {

    static void createNewRecordWinFrame(int recordValue) {
        GameField.timer.stop();
        GameField.frame.setEnabled(false);
        JFrame newRecordWinFrame = new JFrame("Новый рекорд");
        newRecordWinFrame.setSize(300, 200);
        newRecordWinFrame.setResizable(false);
        newRecordWinFrame.setLocationByPlatform(true);
        ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
        newRecordWinFrame.setIconImage(icon.getImage());
        newRecordWinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newRecordWinFrame.setVisible(true);

        newRecordWinFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                GameField.frame.setVisible(true);
                GameField.frame.setEnabled(true);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        newRecordWinFrame.add(panel);

        GridBagConstraints cell1 = new GridBagConstraints();
        cell1.gridx = 0;
        cell1.gridy = 0;
        cell1.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell2 = new GridBagConstraints();
        cell2.gridx = 0;
        cell2.gridy = 1;
        cell2.insets = new Insets(3, 5, 5, 5);

        GridBagConstraints cell3 = new GridBagConstraints();
        cell3.gridx = 0;
        cell3.gridy = 2;
        cell3.insets = new Insets(3, 5, 5, 5);

        String text = "<html>ПОЗДРАВЛЯЕМ!<br>" +
                "Вы установили новый рекорд.<br>" +
                "Хотите увековечить свое имя?</html>";
        JLabel label = new JLabel(text);
        ImageIcon winIcon = new ImageIcon(".\\MinesweeperUI\\src\\resources\\win.jpg");
        label.setIcon(new ImageIcon(winIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        panel.add(label, cell1);

        JTextField textField = new JTextField(17);
        panel.add(textField, cell2);

        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null) {
                    return;
                }
                if ((getLength() + str.length()) <= 16) {
                    super.insertString(offset, str, attr);
                }
            }
        });

        JButton exitButton = new JButton("Начать заново");
        exitButton.addActionListener(e -> {
            String name = textField.getText();
            if (!Objects.equals(name, "")) {
                GameField.recordTable.addNewRecord(name, recordValue);
            }
            GameField.frame.dispose();
            newRecordWinFrame.dispose();
            StartFrame.createStartFrame();
        });
        panel.add(exitButton, cell3);
    }
}