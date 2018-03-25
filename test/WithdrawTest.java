
import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.strategy.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test withdraw strategy in much of the test case.
 *
 * @author Pakanon pantisawat.
 */
public class WithdrawTest {
    private List<Valuable> money = new ArrayList<>();
    private List<Valuable> withdraw = new ArrayList<>();
    private WithdrawStrategy strategy;

    // String currency for easy.
    private final String BAHT = "Baht";
    private final String RINGGIT = "Ringgit";

    /**
     * Setting up the test. Add money in the list and set the strategy to test.
     */
    @Before
    public void setUp() {
        addSampleMoney();
        strategy = new RecursiveWithdraw();
    }

    /**
     * Test withdrawing with impossible transaction like zero, negative or min value of double.
     */
    @Test
    public void testImpossibleWithdraw() {
        withdraw = strategy.withdraw(createValuable(0, BAHT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(-1, BAHT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(Double.MIN_VALUE, BAHT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(0, RINGGIT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(-1, RINGGIT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(Double.MIN_VALUE, RINGGIT), money);
        assertEquals(null, withdraw);
    }

    /**
     * Test withdrawing with slightly greater amount than the total value.
     */
    @Test
    public void testExceedAmountWithdraw() {
        withdraw = strategy.withdraw(createValuable(28, BAHT), money);
        assertEquals(null, withdraw);

        withdraw = strategy.withdraw(createValuable(28, RINGGIT), money);
        assertEquals(null, withdraw);
    }

    /**
     * Test if the strategy gives the currency of transaction correctly.
     */
    @Test
    public void testWithdrawExactCurrency() {
        withdraw = strategy.withdraw(createValuable(17, BAHT), money);
        assertTrue(isCurrencyMatches(withdraw, BAHT));
        withdraw.clear();

        withdraw = strategy.withdraw(createValuable(17, RINGGIT), money);
        assertTrue(isCurrencyMatches(withdraw, RINGGIT));
        withdraw.clear();
    }

    /**
     * Test if the strategy gives the amount of transaction correctly.
     */
    @Test
    public void testWithdrawExactAmount() {
        withdraw = strategy.withdraw(createValuable(15, BAHT), money);
        printList(withdraw);
        assertTrue(isValueMatches(withdraw, 15));
        withdraw.clear();

        withdraw = strategy.withdraw(createValuable(26, RINGGIT), money);
        assertTrue(isValueMatches(withdraw, 26));
        withdraw.clear();
    }

    /**
     * Test if strategy does not change the items in purse.
     */
    @Test
    public void testPurseItemUntouched() {
        List<Valuable> copyVersion = new ArrayList<>(money);
        withdraw = strategy.withdraw(createValuable(10, BAHT), money);
        assertTrue(money.equals(copyVersion));

        withdraw.addAll(strategy.withdraw(createValuable(10, RINGGIT), money));
        assertTrue(money.equals(copyVersion));

        withdraw.clear();
    }

    /**
     * Test if strategy get the reference of actual items in purse, not create the new one.
     */
    @Test
    public void testWithdrawReference() {
        withdraw = strategy.withdraw(createValuable(22, BAHT), money);
        assertTrue(isIdentityMatched(money, withdraw));
        withdraw.clear();

        withdraw = strategy.withdraw(createValuable(11, RINGGIT), money);
        assertTrue(isIdentityMatched(money, withdraw));
        withdraw.clear();
    }

    @Test
    public void testWithdrawEverything() {
        List<Valuable> copy = new ArrayList<>(money);
        withdraw = strategy.withdraw(createValuable(37, BAHT), copy);
        withdraw.addAll(strategy.withdraw(createValuable(37, RINGGIT), copy));
        System.out.println(withdraw.size());
        copy.removeAll(withdraw);
        assertEquals(Collections.emptyList(), copy);
    }

    /**
     * Create Valuable with exact amount and currency.
     *
     * @param value    is the value of the Valuable.
     * @param currency is the currency of the Valuable.
     * @return Valuable with a specified amount and currency.
     */
    private Valuable createValuable(double value, String currency) {
        return new Money(value, currency);
    }

    /**
     * Check if every elements in the list have the correct currency.
     *
     * @param money    is the list of valuable needs to check.
     * @param currency is the currency needed.
     * @return true if all elements have the same currency as the arguments.
     */
    private boolean isCurrencyMatches(List<Valuable> money, String currency) {
        for (Valuable m : money) {
            if (!m.getCurrency().equals(currency)) return false;
        }
        return true;
    }

    /**
     * Check if total value is the exact amount.
     *
     * @param money is the list of valuable needs to check.
     * @param value is the value needed.
     * @return true if all elements have the same value as the arguments.
     */
    private boolean isValueMatches(List<Valuable> money, double value) {
        double total = 0D;
        for (Valuable m : money) total += m.getValue();
        return total == value;
    }

    /**
     * Check if every elements in the withdrew list was referenced from money list.
     *
     * @param money    is the controlling list.
     * @param withdrew is the list to check the element's references.
     * @return true if all elements in withdrew list is referenced from money list.
     */
    private boolean isIdentityMatched(List<Valuable> money, List<Valuable> withdrew) {
        for (Valuable wd : withdrew) {
            if (!money.contains(wd)) return false;
        }
        return true;
    }

    private void printList(List<Valuable> valuables) {
        for (Valuable val : valuables) System.out.print(val.toString() + " ");
        System.out.println();
    }

    /**
     * Add sample money to the list. Contain 27 Baht and Ringgit.
     */
    private void addSampleMoney() {
        // sample value = 37 Baht, 37 Ringgit
        money.add(createValuable(1, BAHT));
        money.add(createValuable(1, BAHT));
        money.add(createValuable(5, BAHT));
        money.add(createValuable(5, BAHT));
        money.add(createValuable(5, BAHT));
        money.add(createValuable(10, BAHT));
        money.add(createValuable(10, BAHT));

        money.add(createValuable(1, RINGGIT));
        money.add(createValuable(1, RINGGIT));
        money.add(createValuable(5, RINGGIT));
        money.add(createValuable(5, RINGGIT));
        money.add(createValuable(5, RINGGIT));
        money.add(createValuable(10, RINGGIT));
        money.add(createValuable(10, RINGGIT));
    }
}