package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
        private static void createStartFrame() {
            JFrame startFrame = new JFrame("Сапер.");
            startFrame.setSize(300, 420);
            startFrame.setResizable(false);
            startFrame.setLocationByPlatform(true);
            ImageIcon icon = new ImageIcon("./MinesweeperUI/src/resources/Minesweeper_icon.jpg");
            startFrame.setIconImage(icon.getImage());
            startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            startFrame.setVisible(true);

            JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            startFrame.add(flow);

            JPanel panel = new JPanel(new GridLayout(7, 1, 1, 1));
            flow.add(panel);


            AtomicInteger secondRest = new AtomicInteger(0);
            JLabel timeRest = new JLabel();
           Timer timer = new Timer(100, e -> {
                secondRest.getAndIncrement();
                timeRest.setText("Время игры " + String.format("%02d:%02d.%01d", secondRest.get() / 600, secondRest.get()/10 % 60, secondRest.get()%10));
            });
            timer.start();
            panel.add(timeRest);


            JLabel levelLabel = new JLabel("Выбери уровень игры:");
            panel.add(levelLabel);

            JButton exitButton = new JButton("выход");
            exitButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON1){
                            System.out.println("Кнопка 1");
                        }
                        if(e.getButton() == MouseEvent.BUTTON2){
                            System.out.println("Кнопка 2");
                        }
                        if(e.getButton() == MouseEvent.BUTTON3){
                            System.out.println("Кнопка 3");
                        }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    super.mouseWheelMoved(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                }
            });
            panel.add(exitButton);
        }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::createStartFrame);
    }
}
