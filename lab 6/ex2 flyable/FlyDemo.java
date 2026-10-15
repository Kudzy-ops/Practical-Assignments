interface Flyable {
    void fly();
}

class Bird implements Flyable {
    private final String species;

    Bird(String species) { this.species = species; }

    @Override
    public void fly() {          // interface methods are public, so the override must be public
        System.out.println(species + " flaps its wings and flies");
    }
}

class Aeroplane implements Flyable {
    private final String model;

    Aeroplane(String model) { this.model = model; }

    @Override
    public void fly() {
        System.out.println(model + " uses jet engines to fly");
    }
}

public class FlyDemo {
    public static void main(String[] args) {
        Flyable[] things = { new Bird("Sparrow"), new Aeroplane("Boeing 737") };
        for (Flyable f : things) {
            f.fly();
        }
    }
}
