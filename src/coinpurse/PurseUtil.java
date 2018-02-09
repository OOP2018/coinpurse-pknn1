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
    private static List<Valuable> money;
    private static int capacity;


    public static Money makeMoney(double value, String currency) {
        if (value >= 20) return new Banknote(value, currency);
        return new Coin(value, currency);
    }

    public static String valToString(List<Valuable> valuables) {
        if (valuables == null) return "NULL";
        return valToString(valuables.toArray(new Valuable[valuables.size()]));
    }

    public static String valToString(Valuable[] valuables) {
        if (valuables == null) return "NULL";
        StringBuilder a = new StringBuilder("[ ");
        System.out.print("[ ");
        for (Valuable val : valuables) {
            a.append(val.toString()).append(" ");
        }
        a.append(" ]");
        return a.toString();
    }

    public static Valuable[] withdraw(Valuable amount) {
        if (amount == null || amount.getValue() < 0) {
            System.out.println("Withdraw amount can't be null or less than 1.");
            return null;
        }

        List<Valuable> sortedMoney = MoneyUtil.filterByCurrency(money, amount.getCurrency());
        money.removeAll(sortedMoney);
        sortedMoney.sort(comparator);

        List<Valuable> withdrawing = new ArrayList<>();
        double amountToWithdraw = amount.getValue();
        for (int i = sortedMoney.size() - 1; i >= 0; i--) {
            if (amountToWithdraw - sortedMoney.get(i).getValue() >= 0) {
                withdrawing.add(sortedMoney.get(i));
                amountToWithdraw -= sortedMoney.get(i).getValue();
                sortedMoney.remove(i);
            }
        }

        if (amountToWithdraw > 0) {
            sortedMoney.addAll(withdrawing);
            money.addAll(sortedMoney);
            return null;
        }
        money.addAll(sortedMoney);
        return withdrawing.toArray(new Valuable[withdrawing.size()]);
    }

    public static Valuable[] withdraw(double amount) {
        Money withdrawingAmount = new Money(amount, "Baht");
        return withdraw(withdrawingAmount);
    }

    public static boolean isFull() {
        return money.size() == capacity;
    }

    public static boolean insert(Valuable valuable) {
        return !isFull() && !(valuable.getValue() <= 0) && money.add(valuable);
    }

    public static double getBalance() {
        double balance = 0;
        for (Valuable val : money) balance += val.getValue();
        return balance;
    }

    public static String staticToString() {
        return String.format("This purse have %d valuables with %d max capacity and total balance %.2f", money.size(), capacity, getBalance());
    }

    public static void testCase(String testName, Object expected, Object actual) {
        if (expected == null) expected = "NULL";
        if (actual == null) actual = "NULL";
        System.out.println("Testing: " + testName);
        System.out.println(String.format("Expected: %s Actual: %s Result: %s", expected, actual, expected.equals(actual) ? "PASS" : "FAIL"));
    }


    public static void testPurse(int capacity, Valuable[] testMoney, Valuable[] transaction) {
        System.out.println("Testing new Purse()");
        Purse purse = new Purse(capacity);
        money = new ArrayList<>();
        PurseUtil.capacity = capacity;
        testCase("getCapacity()", capacity, purse.getCapacity());
        testCase("getBalance()", 0, purse.getBalance());
        testCase("count()", testMoney.length, purse.count());
        testCase("isFull()", isFull(), purse.isFull());
        System.out.println("Inserting money.");
        for (Valuable val : testMoney) {
            testCase(String.format("Insert %.0f %s", val.getValue(), val.getCurrency()), insert(val), purse.insert(val));
        }

        System.out.println("withdraw(double amount)");
        for (Valuable trans : transaction) {
            Valuable[] expected = withdraw(trans.getValue());
            Valuable[] actual = purse.withdraw(trans.getValue());
            testCase(String.format("Withdrawing %.0f Baht", trans.getValue()), valToString(expected), valToString(actual));
        }

        System.out.println("withdraw(Valuable amount)");
        for (Valuable trans : transaction) {
            Valuable[] expected = withdraw(trans);
            Valuable[] actual = purse.withdraw(trans);
            testCase(String.format("Withdrawing %.0f %s", trans.getValue(), trans.getCurrency()), valToString(expected), valToString(actual));
        }
    }

    public static void main(String[] args) {
        Valuable[] valuables = {
                makeMoney(1, "Baht"), makeMoney(1, "Baht"), makeMoney(2, "Baht"),
                makeMoney(1, "Dollar"), makeMoney(1, "Dollar"), makeMoney(2, "Dollar"),
                makeMoney(5, "Baht"), makeMoney(5, "Baht"), makeMoney(10, "Baht"),
                makeMoney(5, "Dollar"), makeMoney(5, "Dollar"), makeMoney(10, "Dollar"),
                makeMoney(20, "Baht"), makeMoney(20, "Baht"), makeMoney(50, "Baht"),
                makeMoney(20, "Dollar"), makeMoney(20, "Dollar"), makeMoney(50, "Dollar"),
                makeMoney(100, "Baht"), makeMoney(50, "Baht"), makeMoney(50, "Baht"),
                makeMoney(100, "Dollar"), makeMoney(50, "Dollar"), makeMoney(50, "Dollar"),
        };

        Money[] transaction = {
                new Money(10, "Baht"), new Money(10, "Baht"), new Money(10, "Dollar"),
                new Money(16, "Baht"), new Money(55, "Dollar"), new Money(44, "Dollar"),
                new Money(7, "Baht"), new Money(32, "Dollar"), new Money(0, "Dollar"),
        };

        testPurse(20, valuables, transaction);
    }

}
