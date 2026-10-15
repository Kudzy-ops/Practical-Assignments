import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    // ---------- registration ----------
    public void addItem(LibraryItem item) {
        if (items.putIfAbsent(item.getId(), item) != null) {
            throw new IllegalArgumentException("Duplicate item id: " + item.getId());
        }
    }

    public void registerMember(Member member) {
        if (members.putIfAbsent(member.getId(), member) != null) {
            throw new IllegalArgumentException("Duplicate member id: " + member.getId());
        }
    }

    public LibraryItem findItem(String id)   { return items.get(id); }
    public Member findMember(String id)      { return members.get(id); }

    // ---------- borrowing and returning ----------
    public Loan borrow(String memberId, String itemId, LocalDate today)
            throws ItemNotAvailableException, MemberLimitExceededException {

        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("Unknown member: " + memberId);

        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("Unknown item: " + itemId);

        item.borrow(member);                    // checks availability and the 3-loan limit

        Loan loan = new Loan(item, member, today.plusDays(item.loanPeriodDays()));
        member.addLoan(loan);
        loans.add(loan);
        return loan;
    }

    public void giveBack(String itemId) {
        Loan loan = loans.stream()
            .filter(l -> l.item().getId().equals(itemId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Item is not on loan: " + itemId));

        loan.item().giveBack();
        loan.member().removeLoan(loan);
        loans.remove(loan);
    }

    // ---------- queries (streams) ----------
    public List<Loan> overdue(LocalDate today) {
        return loans.stream()
            .filter(l -> l.isOverdue(today))
            .sorted(Comparator.comparing(Loan::dueDate))
            .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        String needle = text.toLowerCase();
        return items.values().stream()
            .filter(i -> i.getTitle().toLowerCase().contains(needle))
            .sorted(Comparator.comparing(LibraryItem::getTitle))
            .toList();
    }

    // ---------- persistence (CSV files) ----------
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);

        Files.write(dir.resolve("items.csv"), items.values().stream()
            .map(i -> String.join(",", i.type(), i.getId(), i.getTitle(), i.extra()))
            .toList());

        Files.write(dir.resolve("members.csv"), members.values().stream()
            .map(m -> m.getId() + "," + m.getName())
            .toList());

        Files.write(dir.resolve("loans.csv"), loans.stream()
            .map(l -> l.member().getId() + "," + l.item().getId() + "," + l.dueDate())
            .toList());
    }

    public static Library load(Path dir) throws IOException {
        Library lib = new Library();

        for (String line : readLines(dir.resolve("items.csv"))) {
            String[] p = line.split(",", -1);
            if (p.length != 4) throw new IOException("Bad item line: " + line);
            switch (p[0]) {
                case "BOOK" -> lib.addItem(new Book(p[1], p[2], p[3]));
                case "DVD"  -> lib.addItem(new Dvd(p[1], p[2], Integer.parseInt(p[3])));
                default     -> throw new IOException("Unknown item type in line: " + line);
            }
        }

        for (String line : readLines(dir.resolve("members.csv"))) {
            String[] p = line.split(",", -1);
            if (p.length != 2) throw new IOException("Bad member line: " + line);
            lib.registerMember(new Member(p[0], p[1]));
        }

        for (String line : readLines(dir.resolve("loans.csv"))) {
            String[] p = line.split(",", -1);
            if (p.length != 3) throw new IOException("Bad loan line: " + line);
            Member member = lib.members.get(p[0]);
            LibraryItem item = lib.items.get(p[1]);
            if (member == null || item == null) throw new IOException("Bad loan line: " + line);

            item.setAvailable(false);
            Loan loan = new Loan(item, member, LocalDate.parse(p[2]));
            member.addLoan(loan);
            lib.loans.add(loan);
        }
        return lib;
    }

    // a missing file simply means "nothing saved yet"
    private static List<String> readLines(Path file) throws IOException {
        if (!Files.exists(file)) return List.of();
        return Files.readAllLines(file).stream().filter(s -> !s.isBlank()).toList();
    }
}
