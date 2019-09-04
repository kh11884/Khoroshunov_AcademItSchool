package model;

public class TemperatureCalculator {
    public static double celsiusToKalvin(double gradCelsius) {
        return gradCelsius + 273.15;
    }

    public static double celsiusToFahrenheit(double gradCelsius) {
        return gradCelsius * 9 / 5 + 32;
    }

    public static double kalvinToCelsius(double gradKalvin) {
        return gradKalvin - 273.15;
    }

    public static double kalvinToFahrenheit(double gradKalvin) {
        return gradKalvin * 9 / 5 - 459.67;
    }

    public static double fahrenheitToKalvin(double gradFahrenheit) {
        return (gradFahrenheit + 459.67) / 9 * 5;
    }

    public static double fahrenheitToCelsius(double gradFahrenheit) {
        return (gradFahrenheit - 32) / 9 * 5;
    }
}
