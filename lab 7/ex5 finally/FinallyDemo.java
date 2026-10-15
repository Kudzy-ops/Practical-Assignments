public class FinallyDemo {

    static int tryReturn() {
        int x = 1;
        try {
            System.out.println("in try, returning " + x);
            return x;                       // the value 1 is saved for returning
        } finally {
            x = 99;
            System.out.println("in finally, x is now " + x);
        }
    }

    static String simple() {
        try {
            return "from try";
        } finally {
            System.out.println("finally runs before the method returns");
        }
    }

    // BAD PRACTICE, shown only for understanding: a return in finally replaces the earlier one
    @SuppressWarnings("finally")
    static int bad() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        System.out.println("Returned: " + tryReturn());
        System.out.println("Returned: " + simple());
        System.out.println("bad() returns " + bad());
    }
}
