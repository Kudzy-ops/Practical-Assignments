public class SchoolDemo {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 20, "Computer Science");
        Teacher t = new Teacher("Mr Moyo", 45, "Mathematics");

        s.display();               // inherited from Person
        System.out.println("Course: " + s.getCourse());

        t.display();               // the very same inherited method
        System.out.println("Subject: " + t.getSubject());
    }
}
