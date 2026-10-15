import java.util.EnumMap;
import java.util.Map;

enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int cents;

    Coin(int cents) { this.cents = cents; }     // an enum constructor is implicitly private

    int cents() { return cents; }

    // Greedy: take as many of the largest coin as possible, then move to the next one down
    static Map<Coin, Integer> fewestCoins(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);   // keeps constants in declaration order
        Coin[] all = values();
        int remaining = amount;

        for (int i = all.length - 1; i >= 0; i--) {              // QUARTER first, PENNY last
            int count = remaining / all[i].cents;
            if (count > 0) {
                result.put(all[i], count);
                remaining -= count * all[i].cents;
            }
        }
        return result;
    }
}

public class CoinDemo {
    public static void main(String[] args) {
        int[] amounts = {68, 41, 5, 0};

        for (int amount : amounts) {
            Map<Coin, Integer> coins = Coin.fewestCoins(amount);
            int total = coins.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println(amount + " cents: " + coins + ", total coins = " + total);
        }
    }
}
