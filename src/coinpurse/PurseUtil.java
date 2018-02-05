package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for testing purse functional.
 * Contain method for testing purse with certain test case.
 *
 * @author Pakanon Pantisawat
 */

public class PurseUtil {
    private static Comparator<Valuable> comparator = new ValueComparator();

    /**
     * Test purse functional in certain methods and cases.
     *
     * @param capacity  capacity of the purse which can't be negative number.
     * @param Valuables     Valuables array to put in the purse.
     * @param withdraws withdraws transaction to test the purse.
     */
    private static void purseTest(int capacity, Valuable[] Valuables, double... withdraws) {
        Purse purse = new Purse(capacity);
        System.out.println("========== NEW PURSE ===========");

        printTest("Constructor test with capacity " + capacity, capacity, purse.getCapacity());
        printTest("getBalance() test before adding Valuables", 0.0, purse.getBalance());
        printTest("isFull() test before adding Valuables", false, purse.isFull());

        List<Valuable> testValuable = insertTest(purse, Valuables);
        double total = 0.0;
        for (Valuable Valuable : testValuable) total += Valuable.getValue();

        printTest("getBalance() test after added Valuables", total, purse.getBalance());
        printTest("isFull() test after added Valuables", true, purse.isFull());
        printTest("toString() test", String.format("This purse have %d valuables with %d max capacity and total balance %.2f", capacity, capacity, total), purse.toString());

        for (double transaction : withdraws) {
            testValuable.sort(comparator);
            List<Valuable> expected = new ArrayList<>();
            double amountToWithdraw = transaction;
            System.out.println(stringValuable(testValuable));
            for (int i = testValuable.size() - 1; i >= 0; i--) {
                if (amountToWithdraw - testValuable.get(i).getValue() >= 0) {
                    expected.add(testValuable.get(i));
                    amountToWithdraw -= testValuable.get(i).getValue();
                    testValuable.remove(i);
                    i--;
                }
            }
            if (amountToWithdraw > 0) {
                testValuable.addAll(expected);
                expected = null;
            }
            List<Valuable> actual = new ArrayList<>();
            Valuable[] actualArray = purse.withdraw(transaction);
            if (actualArray == null) actual = null;
            else Collections.addAll(actual, actualArray);
            printTest(String.format("withdraw(%.2f) test.", transaction), stringValuable(expected), stringValuable(actual));
        }
    }

    /**
     * Shortening constructing new Valuable()
     *
     * @param value value of the Valuable, can't be negative.
     * @return Valuable object with defined value in BTC currency.
     */
    private static Valuable makeValuable(double value) {
        return makeValuable(value, "BTC");
    }

    /**
     * Shortening constructing new valuable.
     * This method will differ making coin or banknote depends on value.
     * @param value    value of the valuable, can't be negative.
     * @param currency specific currency for the Valuable.
     * @return valuable object with specific value and currency.
     */
    private static Valuable makeValuable(double value, String currency) {
        if (value > 10) return new Banknote(value, currency);
        return new Coin(value, currency);
    }

    private static List<Valuable> insertTest(Purse purse, Valuable... Valuables) {
        int count = 0;
        List<Valuable> testValuable = new ArrayList<>();
        for (Valuable Valuable : Valuables) {
            boolean expected = count + 1 <= purse.getCapacity() && Valuable.getValue() > 0;
            printTest(String.format("Insert test for %s Valuables", Valuable.toString()), expected, purse.insert(Valuable));

            if (expected) {
                testValuable.add(Valuable);
                count++;
            }
        }
        return testValuable;
    }

    /**
     * Make string format of any number of Valuables.
     *
     * @param Valuables Valuable object
     * @return Readable string of Valuables.
     */
    private static String stringValuable(Valuable... Valuables) {
        if (Valuables == null) return "null";
        StringBuilder ret = new StringBuilder("[ ");
        for (Valuable Valuable : Valuables) ret.append(Valuable.toString()).append(" ");
        ret.append("] ");
        return ret.toString();
    }

    /**
     * Make string format of Valuable's list.
     *
     * @param Valuables List of Valuable object.
     * @return string of Valuables.
     */
    private static String stringValuable(List<Valuable> Valuables) {
        if (Valuables == null) return "null";
        Valuable[] temp = Valuables.toArray(new Valuable[Valuables.size()]);
        return stringValuable(temp);
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
        Valuable[] Valuables1 = {
                makeValuable(5.0), makeValuable(10.0), makeValuable(10.0),
                makeValuable(2.0), makeValuable(2.0), makeValuable(1.0)
        };

        double[] transaction = {12, 10, 2, 4, 5.2, 1};
        purseTest(5, Valuables1, transaction);

        Valuable[] Valuables2 = {
                makeValuable(10.0, "THB"), makeValuable(2.0, "USD"), makeValuable(5.0),
                makeValuable(10.0, "JPY"), makeValuable(1.0, "CNY"), makeValuable(0.5, "THB"),
                makeValuable(0.0), makeValuable(-2.0, "THB"), makeValuable(2.0),
                makeValuable(10.0), makeValuable(5.0, "USD"), makeValuable(1.0)
        };

        purseTest(10, Valuables2, transaction);
    }

}
