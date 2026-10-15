class Employee {
    private final String name;
    protected double baseSalary;

    Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    String getName() { return name; }
    double calculatePay() { return baseSalary; }
}

class Manager extends Employee {
    private final double bonus;

    Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override double calculatePay() { return super.calculatePay() + bonus; }
}

class Developer extends Employee {
    private final int overtimeHours;
    private static final double OVERTIME_RATE = 25.0;

    Developer(String name, double baseSalary, int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override double calculatePay() { return baseSalary + overtimeHours * OVERTIME_RATE; }
}

// NEW class: nothing else in the program has to change
class Intern extends Employee {
    private static final double TRANSPORT_ALLOWANCE = 50.0;

    Intern(String name, double stipend) { super(name, stipend); }

    @Override double calculatePay() { return baseSalary + TRANSPORT_ALLOWANCE; }
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Kudzai", 2000),
            new Manager("Nyasha", 3500, 800),
            new Developer("Tatenda", 3000, 10),
            new Intern("Rudo", 600)
        };

        // This loop knows nothing about Manager, Developer or Intern
        double total = 0;
        for (Employee e : staff) {
            System.out.printf("%s earns %.2f%n", e.getName(), e.calculatePay());
            total += e.calculatePay();
        }
        System.out.printf("Total payroll: %.2f%n", total);
    }
}
