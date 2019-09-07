package model;

import view.TemperatureConverterFrame;

public class TemperatureCalculator {
    public static void calc(TemperatureConverterFrame temperatureConverterFrame) {
        String textIn = temperatureConverterFrame.getValueIn().getText();
        double valueIn = 0;

        try {
            valueIn = Double.parseDouble(textIn);
        } catch (NumberFormatException e) {
            temperatureConverterFrame.setValueIn("0.0");
        }

        Scales scaleIn = (Scales) temperatureConverterFrame.getComboBoxIn().getSelectedItem();
        Scales scaleOut = (Scales) temperatureConverterFrame.getComboBoxOut().getSelectedItem();

        double celsiusIn = scaleIn != null ? scaleIn.getCelsiusValue(valueIn) : 0;
        double degreesOut = scaleOut != null ? scaleOut.getCurrentValue(celsiusIn) : 0;

        temperatureConverterFrame.setResult(degreesOut);
    }
}
