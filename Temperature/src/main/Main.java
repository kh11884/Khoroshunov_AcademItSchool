package main;

import model.TemperatureCalculator;
import view.TemperatureConverterFrame;

import javax.swing.*;

public class Main {
    public static void calc() {
        String scaleIn = (String) TemperatureConverterFrame.getComboBoxIn().getSelectedItem();
        String scaleOut = (String) TemperatureConverterFrame.getComboBoxOut().getSelectedItem();
        String textIn = TemperatureConverterFrame.getValueIn().getText();
        double valueIn = 0;

        try {
            valueIn = Double.parseDouble(textIn);
        } catch (NumberFormatException e) {
            TemperatureConverterFrame.setValueIn("0.0");
        }

        String switchValue = scaleIn + "-" + scaleOut;
        double result;
        switch (switchValue) {
            case ("цельсия-кельвина"):
                result = TemperatureCalculator.celsiusToKalvin(valueIn);
                break;
            case ("цельсия-фаренгейта"):
                result = TemperatureCalculator.celsiusToFahrenheit(valueIn);
                break;
            case ("кельвина-цельсия"):
                result = TemperatureCalculator.kalvinToCelsius(valueIn);
                break;
            case ("кельвина-фаренгейта"):
                result = TemperatureCalculator.kalvinToFahrenheit(valueIn);
                break;
            case ("фаренгейта-кельвина"):
                result = TemperatureCalculator.fahrenheitToKalvin(valueIn);
                break;
            case ("фаренгейта-цельсия"):
                result = TemperatureCalculator.fahrenheitToCelsius(valueIn);
                break;
            default:
                result = valueIn;
                break;
        }
        TemperatureConverterFrame.setResult(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterFrame::createFrame);
    }
}