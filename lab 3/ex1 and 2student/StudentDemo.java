public class StudentDemo {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 85);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> " + s.getGrade());

        s.setMarks(48);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> " + s.getGrade());

        try {
            s.setMarks(150);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Marks are still " + s.getMarks());
    }
}
