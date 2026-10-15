import java.util.List;

public final class ImmutablePerson {          // (1) final class

    private final String name;                // (2) private final fields
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.name = name;
        this.age = age;
        this.hobbies = List.copyOf(hobbies);  // (4) defensive copy on the way IN
    }

    public String getName() { return name; }  // (3) getters only, no setters
    public int getAge()     { return age; }
    public List<String> getHobbies() { return hobbies; }   // already immutable, safe to return

    // (5) "changing" a value produces a NEW object
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(name, newAge, hobbies);
    }

    @Override
    public String toString() { return name + ", " + age + ", " + hobbies; }
}
