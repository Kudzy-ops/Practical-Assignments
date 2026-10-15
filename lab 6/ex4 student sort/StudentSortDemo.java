import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student> {
    private final String name;
    private final int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    String getName() { return name; }
    int getMarks()   { return marks; }

    // natural ordering: alphabetical by name
    @Override
    public int compareTo(Student other) {
        return name.compareTo(other.name);
    }

    @Override
    public String toString() { return name + "(" + marks + ")"; }
}

public class StudentSortDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Tendai", 72));
        list.add(new Student("Anesu", 88));
        list.add(new Student("Chipo", 65));

        Collections.sort(list);                         // uses compareTo
        System.out.println("By name:        " + list);

        // an alternative ordering supplied from outside the class
        Comparator<Student> byMarksDesc = Comparator.comparingInt(Student::getMarks).reversed();
        list.sort(byMarksDesc);
        System.out.println("By marks (high): " + list);
    }
}
