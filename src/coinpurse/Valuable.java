package coinpurse;

/**
 * Interface for valuable items that has value and/or currency.
 *
 * @author Pakanon Pantisawat
 */
public interface Valuable {
    /**
     * Get the value from the attribute.
     * @return value of this valuable item.
     */
    double getValue();

    /**
     * Get the currency from the attribute.
     * @return currency of this valuable item.
     */
    String getCurrency();
}