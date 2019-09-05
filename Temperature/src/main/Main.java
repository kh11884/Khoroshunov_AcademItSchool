package main;

import model.TemperatureCalculator;
import view.TemperatureConverterFrame;

import javax.swing.*;

public class Main {
    public static void calc() {
        String scaleIn = (String) TemperatureConverterFrame.getComboBoxIn().getSelectedItem();
        String scaleOut = (String) TemperatureConverterFrame.getComboBoxOut().getSelectedItem();
        String textIn = TemperatureConverterFrame.getValueIn().getText();
        double valueIn;

        if (textIn != null && !textIn.equals("")) {
            valueIn = Double.parseDouble(textIn);
        } else {
            valueIn = 0;
            TemperatureConverterFrame.setValueIn("0.0");
        }

        double result = 0;
        if (scaleIn == null || scaleOut == null) {
            return;
        }
        System.out.println("КомбоБокс ввод: " + scaleIn);
        System.out.println("КомбоБокс вывод: " + scaleOut);

        if (scaleIn.equals("цельсия") && scaleOut.equals("кельвина")) {
            result = TemperatureCalculator.celsiusToKalvin(valueIn);
        }
        if (scaleIn.equals("цельсия") && scaleOut.equals("фаренгейта")) {
            result = TemperatureCalculator.celsiusToFahrenheit(valueIn);
        }
        if (scaleIn.equals("кельвина") && scaleOut.equals("цельсия")) {
            result = TemperatureCalculator.kalvinToCelsius(valueIn);
        }
        if (scaleIn.equals("кельвина") && scaleOut.equals("фаренгейта")) {
            result = TemperatureCalculator.kalvinToFahrenheit(valueIn);
        }
        if (scaleIn.equals("фаренгейта") && scaleOut.equals("кельвина")) {
            result = TemperatureCalculator.fahrenheitToKalvin(valueIn);
        }
        if (scaleIn.equals("фаренгейта") && scaleOut.equals("цельсия")) {
            result = TemperatureCalculator.fahrenheitToCelsius(valueIn);
        }
        TemperatureConverterFrame.setResult(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterFrame::createFrame);
    }
}