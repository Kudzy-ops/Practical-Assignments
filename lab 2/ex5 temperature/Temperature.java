public class Temperature {

    private final double celsius;

    // private: the only way to get a Temperature is through a factory method
    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double c) {
        return new Temperature(c);
    }

    public static Temperature fromFahrenheit(double f) {
        return new Temperature((f - 32) * 5.0 / 9.0);
    }

    public double getCelsius()    { return celsius; }
    public double getFahrenheit() { return celsius * 9.0 / 5.0 + 32; }

    @Override
    public String toString() {
        return String.format("%.1f C = %.1f F", getCelsius(), getFahrenheit());
    }

    public static void main(String[] args) {
        System.out.println(Temperature.fromCelsius(100));
        System.out.println(Temperature.fromFahrenheit(32));
    }
}
