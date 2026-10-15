public class CarDemo {
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.brand = "Toyota";
        c1.year = 1995;
        c1.mileage = 182000.5;

        Car c2 = new Car();
        c2.brand = "Honda";
        c2.year = 2015;
        c2.mileage = 74500;

        Car c3 = new Car();
        c3.brand = "Ford";
        c3.year = 2001;
        c3.mileage = 120300;

        c1.display();
        c2.display();
        c3.display();

        Car[] cars = { c1, c2, c3 };
        for (Car c : cars) {
            System.out.println(c.brand + " antique? " + c.isAntique());
        }
    }
}
