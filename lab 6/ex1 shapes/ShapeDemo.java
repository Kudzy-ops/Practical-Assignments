abstract class Shape {
    abstract double area();          // no body: every concrete shape must supply one
    abstract double perimeter();

    String name() { return getClass().getSimpleName(); }   // shared, concrete behaviour
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override double area()      { return Math.PI * radius * radius; }
    @Override double perimeter() { return 2 * Math.PI * radius; }
}

class Rectangle extends Shape {
    protected final double width;
    protected final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override double area()      { return width * height; }
    @Override double perimeter() { return 2 * (width + height); }
}

class Square extends Rectangle {
    Square(double side) { super(side, side); }    // a square IS a rectangle with equal sides
}

public class ShapeDemo {
    public static void main(String[] args) {
        // Shape s = new Shape();     // COMPILE ERROR: Shape is abstract

        Shape[] shapes = { new Circle(3), new Rectangle(4, 5), new Square(2.5) };

        for (Shape s : shapes) {
            System.out.printf("%-10s area=%.2f perimeter=%.2f%n", s.name(), s.area(), s.perimeter());
        }
    }
}
