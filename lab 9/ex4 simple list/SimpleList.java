import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleList<T> implements Iterable<T> {

    // STATIC NESTED class: a Node needs no reference to the list that owns it
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) { this.value = value; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int size() { return size; }

    // Implementing Iterable<T> is what makes for-each work
    @Override
    public Iterator<T> iterator() {
        return new NodeIterator();
    }

    // INNER class: each iterator belongs to one list and reads that list's head field directly
    private class NodeIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public static void main(String[] args) {
        SimpleList<String> list = new SimpleList<>();
        list.add("Tariro");
        list.add("Farai");
        list.add("Rudo");

        for (String name : list) {          // compiled into iterator(), hasNext(), next()
            System.out.println(name);
        }
        System.out.println("Size: " + list.size());
    }
}
