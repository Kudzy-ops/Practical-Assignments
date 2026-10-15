import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {
        Path dir = Path.of("library-data");
        Library lib;
        try {
            lib = Library.load(dir);                       // reload saved data on start
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not load data (" + e.getMessage() + "); starting empty");
            lib = new Library();
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1 Add item | 2 Register member | 3 Borrow | 4 Return | 5 Overdue | 6 Search | 0 Save and exit");
            System.out.print("> ");
            if (!in.hasNextLine()) break;
            String choice = in.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        String type = ask(in, "Type (book/dvd)").toLowerCase();
                        String id = ask(in, "Id");
                        String title = ask(in, "Title");
                        if (type.equals("book")) {
                            lib.addItem(new Book(id, title, ask(in, "Author")));
                        } else if (type.equals("dvd")) {
                            lib.addItem(new Dvd(id, title, Integer.parseInt(ask(in, "Minutes"))));
                        } else {
                            throw new IllegalArgumentException("Type must be book or dvd");
                        }
                        System.out.println("Item added.");
                    }
                    case "2" -> {
                        lib.registerMember(new Member(ask(in, "Id"), ask(in, "Name")));
                        System.out.println("Member registered.");
                    }
                    case "3" -> {
                        Loan loan = lib.borrow(ask(in, "Member id"), ask(in, "Item id"), LocalDate.now());
                        System.out.println("Borrowed: " + loan.item().getTitle() + ", due " + loan.dueDate());
                    }
                    case "4" -> {
                        lib.giveBack(ask(in, "Item id"));
                        System.out.println("Returned.");
                    }
                    case "5" -> {
                        List<Loan> late = lib.overdue(LocalDate.now());
                        if (late.isEmpty()) {
                            System.out.println("No overdue items.");
                        }
                        for (Loan l : late) {
                            System.out.println(l.item().getTitle() + " (" + l.member().getName()
                                    + "), due " + l.dueDate());
                        }
                    }
                    case "6" -> {
                        List<LibraryItem> found = lib.searchByTitle(ask(in, "Title contains"));
                        if (found.isEmpty()) {
                            System.out.println("No matches.");
                        }
                        found.forEach(System.out::println);
                    }
                    case "0" -> {
                        lib.save(dir);
                        System.out.println("Saved. Goodbye.");
                        return;
                    }
                    default -> System.out.println("Unknown option.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Not allowed: " + e.getMessage());
            } catch (IllegalArgumentException e) {            // validation errors, bad numbers, unknown ids
                System.out.println("Invalid input: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }
    }

    private static String ask(Scanner in, String prompt) {
        System.out.print(prompt + ": ");
        return in.nextLine().trim();
    }
}
