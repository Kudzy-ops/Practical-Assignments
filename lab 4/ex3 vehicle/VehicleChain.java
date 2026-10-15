class Vehicle {
    Vehicle(String brand) {
        System.out.println("Vehicle constructor: " + brand);
    }
}

class Car extends Vehicle {
    Car(String brand, int doors) {
        super(brand);                          // must be the first statement
        System.out.println("Car constructor: " + doors + " doors");
    }
}

class ElectricCar extends Car {
    ElectricCar(String brand, int doors, int batteryKwh) {
        super(brand, doors);
        System.out.println("ElectricCar constructor: " + batteryKwh + " kWh");
    }
}

public class VehicleChain {
    public static void main(String[] args) {
        new ElectricCar("Tesla", 4, 75);
    }
}
