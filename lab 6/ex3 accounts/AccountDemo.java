abstract class Account {
    private final String owner;
    private double balance;

    Account(String owner, double openingBalance) {
        this.owner = owner;
        this.balance = openingBalance;
    }

    // concrete: identical for every kind of account
    void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    // abstract: each account type has its own fee rule
    abstract double monthlyFee();

    // template: shared logic that calls the abstract step
    void applyMonthlyFee() {
        balance -= monthlyFee();
    }

    String getOwner()   { return owner; }
    double getBalance() { return balance; }
}

class SavingsAccount extends Account {
    SavingsAccount(String owner, double opening) { super(owner, opening); }

    @Override double monthlyFee() { return 0.005 * getBalance(); }   // 0.5% of the balance
}

class CurrentAccount extends Account {
    CurrentAccount(String owner, double opening) { super(owner, opening); }

    @Override double monthlyFee() { return 15.0; }                    // flat fee
}

public class AccountDemo {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Tariro", 1000),
            new CurrentAccount("Farai", 200)
        };
        accounts[0].deposit(500);
        accounts[1].deposit(100);

        for (Account a : accounts) {
            double fee = a.monthlyFee();
            a.applyMonthlyFee();
            System.out.printf("%s (%s): fee %.2f, balance %.2f%n",
                    a.getClass().getSimpleName(), a.getOwner(), fee, a.getBalance());
        }
    }
}
