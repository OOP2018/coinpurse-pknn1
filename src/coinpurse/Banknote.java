package coinpurse;

/**
 * Banknote represents money with a fixed value and currency.
 *
 * @author Pakanon Pantisawat
 */
public class Banknote extends Money {
    /**
     * Next serial number for create new Banknote
     **/
    private static long nextSerialNumber = 100_000_000L;
    private long serialNumber;

    /**
     * Constructor of Banknote, with specific currency and value.
     *
     * @param value    value of the money
     * @param currency currency of the money
     */
    public Banknote(double value, String currency) {
        super(value, currency);
        this.serialNumber = nextSerialNumber;
        nextSerialNumber++;
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
        return String.format("%.0f-%s Note", getValue(), getCurrency());
    }
}