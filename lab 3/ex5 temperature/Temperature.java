public class Temperature {

    private static final double ZERO_C_IN_KELVIN = 273.15;

    private final double kelvin;      // internal representation, hidden from callers

    private Temperature(double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Below absolute zero");
        }
        this.kelvin = kelvin;
    }

    public static Temperature fromCelsius(double c) {
        if (c < -ZERO_C_IN_KELVIN) {
            throw new IllegalArgumentException("Below absolute zero: " + c + " C");
        }
        return new Temperature(c + ZERO_C_IN_KELVIN);
    }

    public static Temperature fromFahrenheit(double f) {
        return fromCelsius((f - 32) * 5.0 / 9.0);
    }

    public double getKelvin()     { return kelvin; }
    public double getCelsius()    { return kelvin - ZERO_C_IN_KELVIN; }
    public double getFahrenheit() { return getCelsius() * 9.0 / 5.0 + 32; }
}
