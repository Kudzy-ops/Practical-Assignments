interface A {
    default void hello() { System.out.println("Hello from A"); }
}

interface B {
    default void hello() { System.out.println("Hello from B"); }
}

class C implements A, B {
    // Without this method the class does NOT compile:
    // "types A and B are incompatible; class C inherits unrelated defaults for hello()"
    @Override
    public void hello() {
        A.super.hello();        // explicitly choose A's version
        B.super.hello();        // and B's version
        System.out.println("Hello from C");
    }
}

public class DefaultConflict {
    public static void main(String[] args) {
        new C().hello();
    }
}
