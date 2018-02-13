package coinpurse;

/**
 * BankNote represents money with a fixed value and currency.
 *
 * @author Pakanon Pantisawat
 */
public class BankNote extends Money {
    /**
     * Next serial number for create new BankNote
     **/
    private static long nextSerialNumber = 100_000_000L;
    private long serialNumber;

    /**
     * Constructor of BankNote, with specific currency and value.
     *
     * @param value    value of the money
     * @param currency currency of the money
     */
    public BankNote(double value, String currency) {
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
     * Get the readable info of this banknote.
     *
     * @return Readable information of this banknote.
     */
    @Override
    public String toString() {
        return String.format("%.0f-%s Note", getValue(), getCurrency());
    }
}