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
            if (o1.getValue() == o2.getValue()) return 0;
            return o1.getValue() > o2.getValue() ? 1 : -1;
        }
        double refValue1 = o1.getCurrency().equalsIgnoreCase("Dollar") ? o1.getValue() * 30 : o1.getValue();
        double refValue2 = o2.getCurrency().equalsIgnoreCase("Dollar") ? o2.getValue() * 30 : o2.getValue();
        return Double.compare(refValue1, refValue2);
    }
}