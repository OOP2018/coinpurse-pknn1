package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable> {

    /**
     * Compare two valuable and return a negative integer, zero, or a positive integer
     * as the first argument is less than, equal to, or greater than the second.
     * and as we consider just only Dollar reference value is 30 Baht.
     *
     * @param o1 Reference Valuable to compare.
     * @param o2 Another reference Valuable to compare.
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Valuable o1, Valuable o2) {
        if (o1.getCurrency().equals(o2.getCurrency())) {
            return Double.compare(o1.getValue(), o2.getValue());
        }
        return o1.getCurrency().compareTo(o2.getCurrency());
    }
}