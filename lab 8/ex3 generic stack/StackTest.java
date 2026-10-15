import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

class MyStack<T> {
    private final List<T> items = new ArrayList<>();

    void push(T item) {
        items.add(item);
    }

    T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    T peek() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    boolean isEmpty() {
        return items.isEmpty();
    }
}

public class StackTest {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("peek: " + stack.peek());
        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());
        System.out.println("isEmpty? " + stack.isEmpty());
        System.out.println("pop: " + stack.pop());
        System.out.println("isEmpty? " + stack.isEmpty());

        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Stack was empty: " + e.getClass().getSimpleName());
        }

        MyStack<String> names = new MyStack<>();      // same class, different type
        names.push("Ada");
        String top = names.peek();                    // no cast needed
        System.out.println("Top name: " + top);
    }
}
