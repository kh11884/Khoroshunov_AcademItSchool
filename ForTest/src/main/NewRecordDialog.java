package main;

import javax.swing.*;
import java.awt.*;


class NewRecordDialog {

    static String createNewRecordDialog(){
        String message = "ПОЗДРАВЛЯЕМ!" + System.lineSeparator() +
                "Вы установили новый рекорд." + System.lineSeparator() +
                "Хотите увековечить свое имя?";
        ImageIcon winIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Khoroshunov_AcademItSchool_new\\MinesweeperUI\\src\\resources\\win.jpg");
        Icon icon = new ImageIcon(winIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JOptionPane pane = new JOptionPane();
        JFrame frame = new JFrame();
        frame.setIconImage(winIcon.getImage());
        return JOptionPane.showInputDialog(frame, message, "Новый рекорд", JOptionPane.INFORMATION_MESSAGE);
    }
}
