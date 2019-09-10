package model;

public class TemperatureCalculator {
    public static double getOutScaleValue(Scales scaleIn, Scales scaleOut, double valueIn) {
        double celsiusIn = scaleIn != null ? scaleIn.getCelsiusValue(valueIn) : 0;
        return scaleOut != null ? scaleOut.getCurrentScaleValue(celsiusIn) : 0;
    }
}
