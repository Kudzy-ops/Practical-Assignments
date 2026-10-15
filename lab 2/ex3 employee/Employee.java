public class Employee {

    private static int nextId = 1;           // shared counter

    private final int employeeId;
    private final String name;
    private final String department;
    private final double salary;

    // Constructor 1: full initialisation (all the real work happens here)
    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;          // assign, then increment
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Constructor 2: default department
    public Employee(String name, double salary) {
        this(name, "General", salary);
    }

    // Constructor 3: default department and salary
    public Employee(String name) {
        this(name, "General", 1000.0);
    }

    // Copy constructor: same details, but it is a NEW employee, so it gets a new id
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public static int getCount() { return nextId - 1; }

    public void display() {
        System.out.printf("#%d %-8s %-8s %.2f%n", employeeId, name, department, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Tariro", "IT", 3000);
        Employee e2 = new Employee("Farai", 2500);
        Employee e3 = new Employee("Rudo");
        Employee e4 = new Employee(e1);

        e1.display();
        e2.display();
        e3.display();
        e4.display();
        System.out.println("Employees created: " + Employee.getCount());
    }
}
