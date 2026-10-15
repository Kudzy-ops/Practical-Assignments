public class Circle {

    // ONE copy shared by every Circle
    private static int count = 0;

    private final double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;      // this.radius = field, radius = parameter
        count++;
    }

    public Circle() {
        this(1.0);                 // constructor chaining: reuse the main constructor
    }

    public double getRadius() { return radius; }

    public double area() { return Math.PI * radius * radius; }

    public static int getCount() { return count; }
}
