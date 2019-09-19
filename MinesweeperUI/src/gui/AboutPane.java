package gui;

import javax.swing.*;

class AboutPane {
    static void createAboutPane() {
        String message = "Игра \"Сапер\":" + System.lineSeparator() +
                "разработана в качестве" + System.lineSeparator() +
                "учебного задания" + System.lineSeparator() +
                "Хорошунова А.В." + System.lineSeparator() +
                System.lineSeparator() +
                "Правила:" + System.lineSeparator() +
                "Игрок открывает ячейки," + System.lineSeparator() +
                "стараясь не открыть ячейку с миной." + System.lineSeparator() +
                "Открыв ячейку с миной, он проигрывает.";
        JOptionPane.showMessageDialog(null, message, "О программе:", JOptionPane.INFORMATION_MESSAGE);
    }
}
