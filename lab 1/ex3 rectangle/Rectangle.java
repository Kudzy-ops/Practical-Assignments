public class Rectangle {
    double width;
    double height;

    double area()      { return width * height; }
    double perimeter() { return 2 * (width + height); }

    public static void main(String[] args) {
        double[][] sizes = { {2, 3}, {4, 5}, {1, 10}, {6, 2}, {3, 3} };

        // new Rectangle[5] only creates 5 empty (null) slots, so each object must be created
        Rectangle[] rects = new Rectangle[5];
        for (int i = 0; i < rects.length; i++) {
            rects[i] = new Rectangle();
            rects[i].width  = sizes[i][0];
            rects[i].height = sizes[i][1];
        }

        Rectangle largest = rects[0];
        for (Rectangle r : rects) {
            if (r.area() > largest.area()) {
                largest = r;
            }
        }

        System.out.println("Largest: " + largest.width + " x " + largest.height
                + ", area = " + largest.area()
                + ", perimeter = " + largest.perimeter());
    }
}
