class InvalidAgeException extends Exception {          // extends Exception => CHECKED
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class AgeDemo {

    // 'throws' tells every caller it must handle or pass on this exception
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age " + age + " is outside 0 to 120");
        }
        System.out.println("Age " + age + " is valid");
    }

    public static void main(String[] args) {
        int[] tests = {25, -3, 130, 120};

        for (int age : tests) {
            try {
                validateAge(age);
            } catch (InvalidAgeException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }
    }
}
