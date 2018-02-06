package coinpurse;

/**
 * Banknote represents money with a fixed value and currency.
 *
 * @author Pakanon Pantisawat
 */
public class Banknote implements Valuable {
    /**
     * Next serial number for create new Banknote
     **/
    private static long nextSerialNumber = 100_000_000L;
    private double value;
    private String currency;
    private long serialNumber;

    /**
     * Constructor of Banknote, with specific currency and value.
     *
     * @param value    value of the money
     * @param currency currency of the money
     */
    public Banknote(double value, String currency) {
        this.value = value;
        this.currency = currency;
        this.serialNumber = nextSerialNumber;
        nextSerialNumber++;
    }

    /**
     * Get the value of the money.
     *
     * @return value of money
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * Get the currency of the money.
     *
     * @return currency of money.
     */
    @Override
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Get the serial number of the money.
     *
     * @return serial number of money.
     */
    public long getSerial() {
        return this.serialNumber;
    }

    /**
     * Check if other banknote and this banknote is equals in both value and currency.
     *
     * @param obj another object to check.
     * @return true if both are equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Banknote other = ((Banknote) obj);
        return this.getCurrency().equals(other.getCurrency()) && this.getValue() == other.getValue();
    }

    /**
     * Get the readable info of this banknote.
     *
     * @return Readable information of this banknote.
     */
    @Override
    public String toString() {
        return String.format("%.0f-%s Note [%d]", value, currency, serialNumber);
    }
}