class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() { return shortfall; }
}

class BankAccount {
    private double balance;

    BankAccount(double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.balance = openingBalance;
    }

    double getBalance() { return balance; }

    void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    // was: boolean withdraw(double amount)
    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Cannot withdraw " + amount + "; balance is " + balance, amount - balance);
        }
        balance -= amount;      // only reached when the withdrawal is allowed
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(500);

        try {
            acc.withdraw(100);
            System.out.println("Balance: " + acc.getBalance());
            acc.withdraw(5000);                  // fails
            System.out.println("never printed");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Short by: " + e.getShortfall());
        }
        System.out.println("Balance unchanged: " + acc.getBalance());
    }
}
