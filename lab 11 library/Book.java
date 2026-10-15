public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank() || author.contains(",")) {
            throw new IllegalArgumentException("A valid author (no commas) is required");
        }
        this.author = author.trim();
    }

    public String getAuthor() { return author; }

    @Override public int loanPeriodDays() { return 21; }
    @Override String type()  { return "BOOK"; }
    @Override String extra() { return author; }
}
