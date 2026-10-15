// This file shows the FIXED version. To see the compile error from the exercise,
// delete the Dog constructor below and compile again:
//   error: constructor Animal in class Animal cannot be applied to given types;

class Animal {
    private final String name;

    Animal(String name) {          // the ONLY constructor, so no default constructor exists
        this.name = name;
    }

    String getName() { return name; }
}

class Dog extends Animal {
    // Fix 1: pass the argument up to Animal's constructor
    Dog(String name) {
        super(name);
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Dog d = new Dog("Rex");
        System.out.println("Dog created: " + d.getName());
    }
}
