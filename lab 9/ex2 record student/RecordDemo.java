record Student(String name, int age) { }

public class RecordDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Tariro", 20);
        Student s2 = new Student("Tariro", 20);
        Student s3 = new Student("Farai", 21);

        System.out.println(s1);                                   // generated toString
        System.out.println("s1.equals(s2): " + s1.equals(s2));    // generated equals (by value)
        System.out.println("s1 == s2: " + (s1 == s2));            // still two different objects
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("Same hash codes: " + (s1.hashCode() == s2.hashCode()));
        System.out.println("name component: " + s1.name());      // accessor is name(), not getName()
    }
}
