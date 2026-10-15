class Animal {
    String sound() { return "..."; }
}

class Cat extends Animal {
    @Override String sound() { return "Meow"; }
}

class Cow extends Animal {
    @Override String sound() { return "Moo"; }
}

class Duck extends Animal {
    @Override String sound() { return "Quack"; }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };   // upcasting

        for (Animal a : zoo) {
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }
    }
}
