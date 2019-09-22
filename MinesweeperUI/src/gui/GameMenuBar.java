package gui;

import javax.swing.*;

class GameMenuBar extends JMenuBar {

    GameMenuBar() {
        add(createFileMenu());
        add(createAboutMenu());
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem newGame = new JMenuItem("Новая игра");
        JMenuItem highScore = new JMenuItem("Таблица рекордов");
        JMenuItem exit = new JMenuItem("Выход");

        newGame.addActionListener(arg0 -> {
            GameField.frame.dispose();
            GameField.timer.stop();
            StartFrame.createStartFrame();
        });

        highScore.addActionListener(arg0 -> RecordFrame.createRecordFrame());

        exit.addActionListener(arg0 -> {
            GameField.frame.dispose();
            GameField.timer.stop();
        });

        file.add(newGame);
        file.addSeparator();
        file.add(highScore);
        file.addSeparator();
        file.add(exit);

        return file;
    }

    private JMenu createAboutMenu() {
        JMenu about = new JMenu("О программе");
        JMenuItem aboutItem = new JMenuItem("О программе");

        aboutItem.addActionListener(arg0 -> AboutPane.createAboutPane());

        about.add(aboutItem);
        return about;
    }
}
