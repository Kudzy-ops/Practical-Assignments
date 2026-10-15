import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

// The flawed inheritance version
class BadStack extends ArrayList<String> {
    void push(String s) { add(s); }
    String pop()        { return remove(size() - 1); }
}

// The composition version
class LifoStack<T> {
    private final List<T> items = new ArrayList<>();    // HAS-A list, hidden from callers

    void push(T item) { items.add(item); }

    T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    T peek() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    boolean isEmpty() { return items.isEmpty(); }
    int size()        { return items.size(); }
}

public class StackDemo {
    public static void main(String[] args) {
        BadStack bad = new BadStack();
        bad.push("A");
        bad.push("B");
        bad.push("C");
        bad.add(0, "X");           // legal, but a real stack can only add at the top
        System.out.println("Bad stack after add(0, X): " + bad);
        bad.remove(1);             // removes from the middle, which a stack forbids
        System.out.println("Bad stack after remove(1):  " + bad);

        LifoStack<Integer> good = new LifoStack<>();
        good.push(1);
        good.push(2);
        good.push(3);
        System.out.println("peek: " + good.peek());
        System.out.println("pop: " + good.pop());
        System.out.println("pop: " + good.pop());
        System.out.println("size: " + good.size());
        // good.add(0, 9);         // COMPILE ERROR: LifoStack has no such method
    }
}
