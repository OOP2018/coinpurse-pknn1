package coinpurse;

/**
 * Money factory provide method for creating money.
 *
 * @author Pakanon Pantisawat
 */
public abstract class MoneyFactory {
    private static MoneyFactory instance = null;

    protected MoneyFactory() {

    }

    /**
     * Get the factory instance of subclasses.
     *
     * @return factory instance.
     */
    public static MoneyFactory getInstance() {
        if (instance == null) {
            MoneyFactory.setFactory(ThaiMoneyFactory.getInstance());
        }
        return instance;
    }

    /**
     * Set factory to specific factory in arguments.
     * @param factory factory to set to.
     */
    public static void setFactory(MoneyFactory factory) {
        instance = factory;
    }

    /**
     * Create money based on specific properties of each currency.
     * @param value value of the money.
     * @return money object.
     */
    public abstract Valuable createMoney(double value);

    /**
     * Create money based on specific properties of each currency.
     * @param value value of the money in string.
     * @return money object.
     */
    public Valuable createMoney(String value) {
        try {
            return createMoney(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can't pass the value as a number.");
        }
    }
}
