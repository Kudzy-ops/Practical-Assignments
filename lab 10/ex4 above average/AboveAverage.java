import java.util.List;

record Student(String name, double mark) { }

public class AboveAverage {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Ada", 78),
            new Student("Ben", 55),
            new Student("Cara", 91),
            new Student("Dan", 62),
            new Student("Eve", 84));

        double average = students.stream()
            .mapToDouble(Student::mark)
            .average()
            .orElse(0);

        List<String> above = students.stream()
            .filter(s -> s.mark() > average)      // keep only those above the average
            .map(Student::name)                   // Student -> name
            .sorted()                             // natural String order: alphabetical
            .toList();

        System.out.println("Class average: " + average);
        System.out.println("Above average: " + above);
    }
}
