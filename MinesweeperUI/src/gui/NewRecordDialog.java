package gui;

import javax.swing.*;


class NewRecordDialog {

    static String createNewRecordDialog(){
        String message = "ПОЗДРАВЛЯЕМ!" + System.lineSeparator() +
                "Вы установили новый рекорд." + System.lineSeparator() +
                "Хотите увековечить свое имя?";
        return JOptionPane.showInputDialog(null, message, "Новый рекорд", JOptionPane.INFORMATION_MESSAGE);
    }
}
