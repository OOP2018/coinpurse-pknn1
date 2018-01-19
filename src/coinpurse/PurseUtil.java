package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for testing purse functional.
 * Contain method for testing purse with certain test case.
 *
 * @author Pakanon Pantisawat
 */

public class PurseUtil {

    /**
     * Test purse functional in certain methods and cases.
     *
     * @param capacity  capacity of the purse which can't be negative number.
     * @param coins     coins array to put in the purse.
     * @param withdraws withdraws transaction to test the purse.
     */
    private static void purseTest(int capacity, Coin[] coins, double... withdraws) {
        Purse purse = new Purse(capacity);
        System.out.println("========== NEW PURSE ===========");

        printTest("Constructor test with capacity " + capacity, capacity, purse.getCapacity());
        printTest("getBalance() test before adding coins", 0.0, purse.getBalance());
        printTest("isFull() test before adding coins", false, purse.isFull());

        List<Coin> testCoin = insertTest(purse, coins);
        double total = 0.0;
        for (Coin coin : testCoin) total += coin.getValue();

        printTest("getBalance() test after added coins", total, purse.getBalance());
        printTest("isFull() test after added coins", true, purse.isFull());
        printTest("toString() test", String.format("This purse have %d coins with %d max capacity and total balance %.2f", capacity, capacity, total), purse.toString());

        for (double transaction : withdraws) {
            Collections.sort(testCoin);
            List<Coin> expected = new ArrayList<>();
            double amountToWithdraw = transaction;
            for (int i = testCoin.size() - 1; i >= 0; i--) {
                if (amountToWithdraw - testCoin.get(i).getValue() >= 0) {
                    expected.add(testCoin.get(i));
                    amountToWithdraw -= testCoin.get(i).getValue();
                    testCoin.remove(i);
                }
            }
            if (amountToWithdraw > 0) {
                testCoin.addAll(expected);
                expected = null;
            }
            List<Coin> actual = new ArrayList<>();
            Coin[] actualArray = purse.withdraw(transaction);
            if (actualArray == null) actual = null;
            else Collections.addAll(actual, actualArray);
            printTest(String.format("withdraw(%.2f) test.", transaction), stringCoin(expected), stringCoin(actual));
        }
    }

    /**
     * Shortening constructing new Coin()
     *
     * @param value value of the coin, can't be negative.
     * @return Coin object with defined value in BTC currency.
     */
    private static Coin makeCoin(double value) {
        return makeCoin(value, "BTC");
    }

    /**
     * Shortening constructing new Coin()
     *
     * @param value    value of the coin, can't be negative.
     * @param currency specific currency for the coin.
     * @return Coin object with specific value and currency.
     */
    private static Coin makeCoin(double value, String currency) {
        return new Coin(value, currency);
    }

    private static List<Coin> insertTest(Purse purse, Coin... coins) {
        int count = 0;
        List<Coin> testCoin = new ArrayList<>();
        for (Coin coin : coins) {
            boolean expected = count + 1 <= purse.getCapacity() && coin.getValue() > 0;
            printTest(String.format("Insert test for %s coins", coin.toString()), expected, purse.insert(coin));

            if (expected) {
                testCoin.add(coin);
                count++;
            }
        }
        return testCoin;
    }

    /**
     * Make string format of any number of coins.
     *
     * @param coins Coin object
     * @return Reable string of Coins.
     */
    private static String stringCoin(Coin... coins) {
        if (coins == null) return "null";
        StringBuilder ret = new StringBuilder("[ ");
        for (Coin coin : coins) ret.append(coin.toString()).append(" ");
        ret.append("] ");
        return ret.toString();
    }

    /**
     * Make string format of Coin's list.
     *
     * @param coins List of Coin object.
     * @return string of Coins.
     */
    private static String stringCoin(List<Coin> coins) {
        if (coins == null) return "null";
        Coin[] temp = coins.toArray(new Coin[coins.size()]);
        return stringCoin(temp);
    }

    /**
     * Check test case and print to user if it's pass the case or fail.
     *
     * @param prompt   prompt message for test case.
     * @param expected expected value for the case.
     * @param actual   actual value from the method.
     */
    private static void printTest(String prompt, Object expected, Object actual) {
        System.out.println(prompt);
        System.out.printf("Expected: %s, Actual: %s. Result is %s%n%n", expected, actual, expected.equals(actual) ? "PASS" : "FAIL");
    }

    /**
     * All the test case configuring here
     * @param args not used in this method.
     */
    public static void main(String[] args) {
        Coin[] coins1 = {
                makeCoin(5.0), makeCoin(10.0), makeCoin(10.0),
                makeCoin(2.0), makeCoin(0.0), makeCoin(2.0),
                makeCoin(1.0), makeCoin(5.0)
        };

        double[] transaction = {12, 10, 2, 4, 5.2, 1};

        purseTest(5, coins1, transaction);

        Coin[] coins2 = {
                makeCoin(10.0, "THB"), makeCoin(2.0, "USD"), makeCoin(5.0),
                makeCoin(10.0, "JPY"), makeCoin(1.0, "CNY"), makeCoin(0.5, "THB"),
                makeCoin(0.0), makeCoin(-2.0, "THB"), makeCoin(2.0),
                makeCoin(10.0), makeCoin(5.0, "USD"), makeCoin(1.0)
        };

        purseTest(10, coins2, transaction);
    }

}
