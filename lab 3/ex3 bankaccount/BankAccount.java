import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final String accountNumber;
    private String ownerName;
    private double balance;
    private final List<String> history = new ArrayList<>();   // private and never handed out directly

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name required");
        }
        this.accountNumber = accountNumber;
        this.ownerName = ownerName.trim();
        this.balance = openingBalance;
        history.add(String.format("OPEN %.2f", openingBalance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
        history.add(String.format("DEPOSIT %.2f", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            history.add(String.format("FAILED WITHDRAW %.2f", amount));
            return false;
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f", amount));
        return true;
    }

    // List.copyOf returns an immutable SNAPSHOT: callers cannot change it,
    // and later transactions do not alter a copy they already hold
    public List<String> getHistory() {
        return List.copyOf(history);
    }
}
