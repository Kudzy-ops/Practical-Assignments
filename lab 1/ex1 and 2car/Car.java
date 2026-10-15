public class Car {
    String brand;
    int year;
    double mileage;   // kilometres

    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    boolean isAntique() {
        int age = java.time.Year.now().getValue() - year;
        return age > 25;      // strictly MORE than 25 years
    }
}
