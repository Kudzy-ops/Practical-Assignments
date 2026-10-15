import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Book {
    private final String isbn;
    private final String title;

    Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // Two books are the same book if they have the same ISBN (the title is ignored)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;      // also handles null
        return isbn.equals(other.isbn);
    }

    // Must use the SAME field(s) as equals, so equal books give equal hash codes
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() { return title + " [" + isbn + "]"; }
}

public class BookSetDemo {
    public static void main(String[] args) {
        Set<Book> shelf = new HashSet<>();

        System.out.println("Add first copy:     " + shelf.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("Add second copy:    " + shelf.add(new Book("978-0132350884", "Clean Code (2nd copy)")));
        System.out.println("Add different book: " + shelf.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("Set size: " + shelf.size());
    }
}
