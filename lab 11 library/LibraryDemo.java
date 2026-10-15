import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class LibraryDemo {
    public static void main(String[] args) throws Exception {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency in Practice", "Brian Goetz"));
        lib.addItem(new Dvd("D1", "The Imitation Game", 114));
        lib.registerMember(new Member("M1", "Tariro"));
        lib.registerMember(new Member("M2", "Farai"));

        LocalDate start = LocalDate.of(2026, 10, 1);

        // Tariro borrows three items (due: B1 and B2 after 21 days, the DVD after 7 days)
        lib.borrow("M1", "B1", start);
        lib.borrow("M1", "D1", start);
        lib.borrow("M1", "B2", start);
        System.out.println("Tariro now has " + lib.findMember("M1").getLoans().size() + " loans");

        // Test 1: the 3-loan limit
        try {
            lib.borrow("M1", "B3", start);
        } catch (ItemNotAvailableException | MemberLimitExceededException e) {
            System.out.println("Not allowed: " + e.getMessage());
        }

        // Test 2: an item that is already on loan
        try {
            lib.borrow("M2", "B1", start);
        } catch (ItemNotAvailableException | MemberLimitExceededException e) {
            System.out.println("Not allowed: " + e.getMessage());
        }

        // Test 3: overdue items on 15 October 2026
        LocalDate today = LocalDate.of(2026, 10, 15);
        for (Loan l : lib.overdue(today)) {
            System.out.println("Overdue on " + today + ": " + l.item().getTitle()
                    + " (" + l.member().getName() + "), due " + l.dueDate());
        }

        // Test 4: return the DVD
        lib.giveBack("D1");
        System.out.println("After return, overdue count: " + lib.overdue(today).size());

        // Test 5: stream-based search
        System.out.println("Search 'java': "
                + lib.searchByTitle("java").stream().map(LibraryItem::getTitle).toList());

        // Test 6: save to disk and load again
        Path dir = Files.createTempDirectory("library-demo");
        lib.save(dir);
        Library reloaded = Library.load(dir);
        System.out.println("After reload: " + reloaded.findItem("B1"));
        System.out.println("Loans for Tariro after reload: " + reloaded.findMember("M1").getLoans().size());
    }
}
