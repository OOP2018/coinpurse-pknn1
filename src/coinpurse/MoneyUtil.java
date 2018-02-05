package coinpurse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for testing valuable functional.
 * Contain method for sorting valuable (for test compareTo() method).
 */
public class MoneyUtil {
    /**
     * Comparator for value comparing
     **/
    private static Comparator<Valuable> comparator = new ValueComparator();
    /**
     * Compare the list before and after sorting to check if compareTo is functional
     * @param valuables a list of valuables consists of the test valuables.
     */
    public static void sortValuable(List<Valuable> valuables) {
        System.out.println("The value before sorting");
        printValuable(valuables);

        valuables.sort(comparator);

        System.out.println("The value after sorting");
        printValuable(valuables);
    }

    /**
     * Filter the list by the currency provided
     * @param valuables List of valuables to filter
     * @param currency Currency to filter
     * @return A filtered list of valuables.
     */
    public static List<Valuable> filterByCurrency(List<Valuable> valuables, String currency) {
        List<Valuable> filteredList = new ArrayList<>();
        for (Valuable valuable : valuables) {
            if (currency.equals(valuable.getCurrency())) filteredList.add(valuable);
        }
        return filteredList;
    }

    /**
     * Print list of valuable
     * @param valuables List of a valuables to print.
     */
    public static void printValuable(List<Valuable> valuables) {
        System.out.print("[ ");
        for (Valuable valuable : valuables) System.out.print(valuable.toString() + " ");
        System.out.println("]");
        System.out.println();
    }

    public static void main(String[] args) {

        List<Valuable> valuables = new ArrayList<>();

        valuables.add(new Coin(10.0, "Baht"));
        valuables.add(new Banknote(0.5, "Baht"));
        valuables.add(new Coin(2.0, "Baht"));
        valuables.add(new Banknote(1.0, "Baht"));
        valuables.add(new Banknote(2.0, "Baht"));
        valuables.add(new Coin(0.5, "Baht"));
        valuables.add(new Banknote(0.25, "Baht"));
        valuables.add(new Coin(10.0, "Baht"));
        valuables.add(new Banknote(10.0, "Rupee"));
        valuables.add(new Coin(1.0, "Dollar"));

        sortValuable(valuables);
        List<Valuable> filteredList = filterByCurrency(valuables, "Baht");
        printValuable(filteredList);
    }
}
