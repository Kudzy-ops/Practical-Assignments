public class CircleDemo {
    public static void main(String[] args) {
        Circle c1 = new Circle(2.5);
        Circle c2 = new Circle();          // radius defaults to 1.0

        System.out.printf("c1: radius=%.1f area=%.2f%n", c1.getRadius(), c1.area());
        System.out.printf("c2: radius=%.1f area=%.2f%n", c2.getRadius(), c2.area());
        System.out.println("Circles created: " + Circle.getCount());
    }
}
