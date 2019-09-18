package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator"); // создаём фрейм нашей программы

        JPanel mainPane = new JPanel(); // создаём панель, на которой будет лежать текстовое поле
        JTextArea textarea = new JTextArea();

        mainPane.add(textarea); // добавляем на панель

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.append("5"); // ставим задачу - добавлять пятерку в textarea
            }
        };
        Timer timer = new Timer( 100, listener ); // ставим задачу на таймер на каждые 100 милисекунд
        timer.start(); // запускаем таймер

        frame.setContentPane(mainPane); // кидаем панель в JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
