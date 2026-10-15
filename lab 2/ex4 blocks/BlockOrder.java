public class BlockOrder {

    static { System.out.println("static block 1"); }

    { System.out.println("instance block 1"); }

    BlockOrder() { System.out.println("constructor"); }

    static { System.out.println("static block 2"); }

    { System.out.println("instance block 2"); }

    public static void main(String[] args) {
        System.out.println("main starts");
        new BlockOrder();
        new BlockOrder();
    }
}
