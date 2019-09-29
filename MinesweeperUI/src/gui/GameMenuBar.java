package gui;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class GameMenuBar {
    private JMenuBar gameMenuBar = new JMenuBar();

    GameMenuBar() {
        gameMenuBar.add(createFileMenu());
        gameMenuBar.add(createAboutMenu());
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem newGame = new JMenuItem("Новая игра");
        JMenuItem highScore = new JMenuItem("Таблица рекордов");
        JMenuItem exit = new JMenuItem("Выход");

        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newGame.addActionListener(arg0 -> {
            GameField.frame.dispose();
            GameField.timer.stop();
            StartFrame.createStartFrame();
        });

        highScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        highScore.addActionListener(arg0 -> RecordFrame.createRecordFrame());

        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
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

        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        aboutItem.addActionListener(arg0 -> AboutPane.createAboutPane());

        about.add(aboutItem);
        return about;
    }

    JMenuBar getJMenu() {
        return gameMenuBar;
    }
}
