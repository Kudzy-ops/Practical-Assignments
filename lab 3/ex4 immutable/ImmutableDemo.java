import java.util.ArrayList;
import java.util.List;

public class ImmutableDemo {
    public static void main(String[] args) {
        List<String> source = new ArrayList<>(List.of("Reading", "Chess"));
        ImmutablePerson p = new ImmutablePerson("Tariro", 20, source);
        System.out.println(p);

        source.add("Gaming");     // changing the caller's own list
        System.out.println("After changing source list: " + p.getHobbies());

        ImmutablePerson older = p.withAge(21);
        System.out.println("Older copy: " + older.getName() + ", " + older.getAge());
        System.out.println("Original still: " + p.getName() + ", " + p.getAge());
    }
}
