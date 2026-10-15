public abstract class LibraryItem implements Borrowable {

    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item id is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (id.contains(",") || title.contains(",")) {      // commas would break the CSV files
            throw new IllegalArgumentException("Commas are not allowed in ids or titles");
        }
        this.id = id.trim();
        this.title = title.trim();
    }

    public abstract int loanPeriodDays();     // each kind of item decides its own loan period
    abstract String type();                   // used by the CSV file: BOOK or DVD
    abstract String extra();                  // author or running time, used by the CSV file

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }

    void setAvailable(boolean available) { this.available = available; }   // package-private

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) {
            throw new ItemNotAvailableException(title + " is already on loan");
        }
        member.ensureCanBorrow();             // throws if the member already has 3 loans
        available = false;
    }

    @Override
    public void giveBack() {
        available = true;
    }

    @Override
    public String toString() {
        return String.format("%s %s \"%s\" [%s]", type(), id, title, available ? "available" : "on loan");
    }
}
