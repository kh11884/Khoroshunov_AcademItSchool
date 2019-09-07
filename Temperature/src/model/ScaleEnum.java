package model;

public enum ScaleEnum {
    KELVIN("кельвина") {
        @Override
        public double getCurrentValue(double value) {
            return value + 273.15;
        }

        @Override
        public double getCelsiusValue(double value) {
            return value - 273.15;
        }
    },
    CELSIUS("цельсия") {
        @Override
        public double getCurrentValue(double value) {
            return value;
        }

        @Override
        public double getCelsiusValue(double value) {
            return value;
        }
    },
    FAHRENHEIT("фанергейта") {
        @Override
        public double getCurrentValue(double value) {
            return value * 1.8 + 32;
        }

        @Override
        public double getCelsiusValue(double value) {
            return (value - 32) * 5 / 9;
        }
    };

    private final String name;

    ScaleEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract double getCurrentValue(double value);

    public abstract double getCelsiusValue(double value);
}
