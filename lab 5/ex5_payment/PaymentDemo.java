// ===================== VERSION 1: instanceof checks =====================
interface Payment { double amount(); }

record CardPayment(String cardNumber, double amount) implements Payment { }
record MobileMoneyPayment(String phone, double amount) implements Payment { }
record CashPayment(double amount) implements Payment { }     // added later

class PaymentProcessor {
    static void pay(Payment p) {
        if (p instanceof CardPayment c) {
            String last4 = c.cardNumber().substring(c.cardNumber().length() - 4);
            System.out.printf("Card ****%s charged %.2f%n", last4, c.amount());
        } else if (p instanceof MobileMoneyPayment m) {
            System.out.printf("Mobile money from %s: %.2f%n", m.phone(), m.amount());
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + p.getClass().getSimpleName());
        }
    }
}

// ================ VERSION 2: polymorphic process() method ================
interface PolyPayment { void process(); }

record PolyCard(String cardNumber, double amount) implements PolyPayment {
    public void process() {
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        System.out.printf("Card ****%s charged %.2f%n", last4, amount);
    }
}

record PolyMobile(String phone, double amount) implements PolyPayment {
    public void process() {
        System.out.printf("Mobile money from %s: %.2f%n", phone, amount);
    }
}

record PolyCash(double amount) implements PolyPayment {   // new type: no existing code edited
    public void process() {
        System.out.printf("Cash received: %.2f%n", amount);
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        System.out.println("Version 1:");
        PaymentProcessor.pay(new CardPayment("4111111111111234", 120));
        PaymentProcessor.pay(new MobileMoneyPayment("0771234567", 45.5));
        try {
            PaymentProcessor.pay(new CashPayment(10));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Version 2:");
        PolyPayment[] payments = {
            new PolyCard("4111111111111234", 120),
            new PolyMobile("0771234567", 45.5),
            new PolyCash(10)
        };
        for (PolyPayment p : payments) {
            p.process();
        }
    }
}
