package model;

public enum Scales {
    KELVIN("кельвина") {
        @Override
        public double getCurrentScaleValue(double celsiusValue) {
            return celsiusValue + 273.15;
        }

        @Override
        public double getCelsiusValue(double value) {
            return value - 273.15;
        }
    },
    CELSIUS("цельсия") {
        @Override
        public double getCurrentScaleValue(double celsiusValue) {
            return celsiusValue;
        }

        @Override
        public double getCelsiusValue(double value) {
            return value;
        }
    },
    FAHRENHEIT("фаренгейта") {
        @Override
        public double getCurrentScaleValue(double celsiusValue) {
            return celsiusValue * 1.8 + 32;
        }

        @Override
        public double getCelsiusValue(double value) {
            return (value - 32) * 5 / 9;
        }
    };

    private final String name;

    Scales(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract double getCurrentScaleValue(double celsiusValue);

    public abstract double getCelsiusValue(double value);
}
