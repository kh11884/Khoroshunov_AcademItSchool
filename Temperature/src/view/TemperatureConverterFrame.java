package view;

import model.Scales;
import model.TemperatureCalculator;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterFrame {
    private JTextField valueIn;
    private JTextField result;
    private JComboBox<Scales> comboBoxIn;
    private JComboBox<Scales> comboBoxOut;

    public void setResult(double result) {
        this.result.setText(String.format("%.3f", result));
    }

    public JComboBox<Scales> getComboBoxIn() {
        return comboBoxIn;
    }

    public JComboBox<Scales> getComboBoxOut() {
        return comboBoxOut;
    }

    public JTextField getValueIn() {
        return valueIn;
    }

    public void setValueIn(String valueIn) {
        this.valueIn.setText(valueIn);
    }

    public void createFrame() {
        JFrame frame = new JFrame("Конвертер температур");
        frame.setSize(320, 260);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("./Temperature/src/view/image/icon.jpg");
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

        JLabel label1 = new JLabel("   КОНВЕРТЕР ТЕМПЕРАТУР");
        label1.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        panel.add(label1, cell_0_0);

        JLabel label2 = new JLabel("Шкала ввода");
        panel.add(label2, cell_0_1);

        JLabel label3 = new JLabel("Шкала вывода");
        panel.add(label3, cell_1_1);

        JLabel label4 = new JLabel("Исходное значение");
        panel.add(label4, cell_0_3);

        JLabel label5 = new JLabel("Результат");
        panel.add(label5, cell_1_3);

        comboBoxIn = new JComboBox<>(Scales.values());
        panel.add(comboBoxIn, cell_0_2);

        comboBoxOut = new JComboBox<>(Scales.values());
        panel.add(comboBoxOut, cell_1_2);

        valueIn = new JTextField("", 10);
        panel.add(valueIn, cell_0_4);

        result = new JTextField("", 10);
        result.setEditable(false);
        panel.add(result, cell_1_4);

        JButton calcButton = new JButton("Рассчитать");
        calcButton.addActionListener(e -> TemperatureCalculator.calc(this));
        panel.add(calcButton, cell_0_5);

        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(e -> frame.dispose());
        panel.add(closeButton, cell_1_5);
    }
}

