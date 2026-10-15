public class ArrayAccess {

    static int elementAt(int[] data, int index) {
        try {
            return data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index " + index + " is invalid for an array of length " + data.length);
            return -1;       // sentinel value meaning "not found"
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};

        System.out.println("Element 1: " + elementAt(data, 1));
        System.out.println("Result: " + elementAt(data, 5));
    }
}
