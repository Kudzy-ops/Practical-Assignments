class Parent {
    String label = "Parent field";
    String who() { return "Parent method"; }
    static String kind() { return "Parent static"; }
}

class Child extends Parent {
    String label = "Child field";                    // hides the field, does not override it
    @Override String who() { return "Child method"; }
    static String kind() { return "Child static"; }  // hides the static method
}

public class FieldVsMethod {
    public static void main(String[] args) {
        Parent ref = new Child();       // declared type Parent, actual object Child

        System.out.println(ref.who());      // method: chosen by the ACTUAL object
        System.out.println(ref.label);      // field: chosen by the DECLARED type
        System.out.println(ref.kind());     // static method: chosen by the DECLARED type

        Child c = (Child) ref;              // same object, viewed as Child
        System.out.println(c.label);
    }
}
