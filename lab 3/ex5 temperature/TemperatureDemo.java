public class TemperatureDemo {
    public static void main(String[] args) {
        Temperature boiling = Temperature.fromCelsius(100);
        System.out.printf("%.1f C = %.1f F = %.2f K%n",
                boiling.getCelsius(), boiling.getFahrenheit(), boiling.getKelvin());

        Temperature absoluteZero = Temperature.fromCelsius(-273.15);
        System.out.printf("Lowest allowed: %.2f K%n", absoluteZero.getKelvin());

        try {
            Temperature.fromCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
