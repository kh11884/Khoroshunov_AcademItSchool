package view;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterFrame {
    private JButton calcButton;
    private JButton closeButton;
    private JTextField valueIn;
    private JTextField valueOut;


    public static void createFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Конвертер температур");
                frame.setSize(300, 260);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                ImageIcon icon = new ImageIcon("./ForTest/src/view/image/icon.jpg");
                frame.setIconImage(icon.getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JPanel panel = new JPanel(new GridBagLayout());
                frame.add(panel);
                GridBagConstraints cell_0_0 = new GridBagConstraints();
                cell_0_0.gridx = 0;
                cell_0_0.gridy = 0;
                cell_0_0.gridwidth = 2;
                Insets insets = new Insets(3, 5, 5, 5);
                cell_0_0.insets = insets;
                GridBagConstraints cell_0_1 = new GridBagConstraints();
                cell_0_1.gridx = 0;
                cell_0_1.gridy = 1;
                cell_0_1.insets = insets;
                GridBagConstraints cell_0_2 = new GridBagConstraints();
                cell_0_2.gridx = 0;
                cell_0_2.gridy = 2;
                cell_0_2.insets = insets;
                GridBagConstraints cell_0_3 = new GridBagConstraints();
                cell_0_3.gridx = 0;
                cell_0_3.gridy = 3;
                cell_0_3.insets = insets;
                GridBagConstraints cell_0_4 = new GridBagConstraints();
                cell_0_4.gridx = 0;
                cell_0_4.gridy = 4;
                cell_0_4.insets = insets;
                GridBagConstraints cell_0_5 = new GridBagConstraints();
                cell_0_5.gridx = 0;
                cell_0_5.gridy = 5;
                cell_0_5.insets = insets;
                GridBagConstraints cell_1_1 = new GridBagConstraints();
                cell_1_1.gridx = 1;
                cell_1_1.gridy = 1;
                cell_1_1.insets = insets;
                GridBagConstraints cell_1_2 = new GridBagConstraints();
                cell_1_2.gridx = 1;
                cell_1_2.gridy = 2;
                cell_1_2.insets = insets;
                GridBagConstraints cell_1_3 = new GridBagConstraints();
                cell_1_3.gridx = 1;
                cell_1_3.gridy = 3;
                cell_1_3.insets = insets;
                GridBagConstraints cell_1_4 = new GridBagConstraints();
                cell_1_4.gridx = 1;
                cell_1_4.gridy = 4;
                cell_1_4.insets = insets;
                GridBagConstraints cell_1_5 = new GridBagConstraints();
                cell_1_5.gridx = 1;
                cell_1_5.gridy = 5;
                cell_1_5.insets = insets;

                JLabel label1 = new JLabel("КОНВЕРТЕР ТЕМПЕРАТУР");
                panel.add(label1, cell_0_0);
                JLabel label2 = new JLabel("Шкала ввода");
                panel.add(label2, cell_0_1);
                JLabel label3 = new JLabel("Шкала вывода");
                panel.add(label3, cell_1_1);
                JLabel label4 = new JLabel("Значение ввода");
                panel.add(label4, cell_0_3);
                JLabel label5 = new JLabel("Результат");
                panel.add(label5, cell_1_3);

                String[] scales = {
                        "цельсия",
                        "фаренгейта",
                        "кельвина"
                };
                JComboBox<String> comboBoxIn = new JComboBox<>(scales);
                panel.add(comboBoxIn, cell_0_2);
                JComboBox<String> comboBoxOut = new JComboBox<>(scales);
                panel.add(comboBoxOut, cell_1_2);

                JTextField textIn = new JTextField("", 10);
                panel.add(textIn, cell_0_4);
                JTextField textOut = new JTextField("", 10);
                textOut.setEditable(false);
                panel.add(textOut, cell_1_4);

                JButton calcButton = new JButton("Рассчитать");
                panel.add(calcButton, cell_0_5);
                JButton closeButton = new JButton("Закрыть");
                panel.add(closeButton, cell_1_5);
            }
        });
    }
}
