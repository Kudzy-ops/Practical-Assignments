import java.util.List;

public class HistoryDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500);
        acc.deposit(250);
        acc.withdraw(100);
        acc.withdraw(5000);        // fails: insufficient funds

        List<String> copy = acc.getHistory();
        for (String entry : copy) {
            System.out.println(entry);
        }
        System.out.printf("Balance: %.2f%n", acc.getBalance());

        try {
            copy.clear();          // attack on the history
        } catch (UnsupportedOperationException e) {
            System.out.println("History copy cannot be modified");
        }
        System.out.println("Real history still has " + acc.getHistory().size() + " entries");
    }
}
