package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static void sortMoney(List<? extends Valuable> valuables) {
        valuables.sort(comparator);
    }

    /**
     * Filter the list by the currency provided
     * @param valuables List of valuables to filter
     * @param currency Currency to filter
     * @return A filtered list of valuables.
     */
    public static <E extends Valuable> List<E> filterByCurrency(List<E> valuables, String currency) {
        List<E> filteredList = new ArrayList<>();
        for (E valuable : valuables) {
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

    public static <T extends Comparable<? super T>> T max (T ... args) {
        if (args == null || args.length <= 0) throw new IllegalArgumentException("args must be given.");
        if (args.length == 1) return args[0];
        T max = args[0].compareTo(args[1]) >= 0? args[0] : args[1];
        for (int index = 2; index < args.length - 1; index++) {
            if (max.compareTo(args[index]) <= 0) max = args[index];
        }
        return max;
    }


    public static void main(String[] args) {
        Money m1 = new BankNote(100, "Baht");
        Money m2 = new BankNote(500, "Baht");
        Money m3 = new Coin(20, "Baht");
        Money max = MoneyUtil.max( m1, m2, m3 );

        System.out.println(max.toString());

    }
}
