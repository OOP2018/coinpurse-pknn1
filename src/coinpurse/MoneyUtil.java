package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing coin functional.
 * Contain method for sorting coin (for test compareTo() method).
 */
public class MoneyUtil {

    /**
     * Compare the list before and after sorting to check if compareTo is functional
     * @param coins a list of coins consists of the test coins.
     */
    public static void sortCoins(List<Coin> coins) {
        System.out.println("The value before sorting");
        System.out.print("[ ");
        for (Coin coin : coins) System.out.print(coin.toString() + " ");
        System.out.print("] ");
        System.out.println();

        java.util.Collections.sort(coins);

        System.out.println("The value after sorting");
        System.out.print("[ ");
        for (Coin coin : coins) System.out.print(coin.toString() + " ");
        System.out.print("] ");
        System.out.println();
    }

    public static void main(String[] args) {

        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(10.0, "Baht"));
        coins.add(new Coin(0.5, "Baht"));
        coins.add(new Coin(2.0, "Baht"));
        coins.add(new Coin(1.0, "Baht"));
        coins.add(new Coin(2.0, "Baht"));
        coins.add(new Coin(0.5, "Baht"));
        coins.add(new Coin(0.25, "Baht"));
        coins.add(new Coin(10.0, "Baht"));
        coins.add(new Coin(10.0, "Rupee"));
        coins.add(new Coin(1.0, "Dollar"));

        sortCoins(coins);
    }
}
