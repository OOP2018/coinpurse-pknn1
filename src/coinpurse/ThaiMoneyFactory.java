package coinpurse;

/**
 * Money Factory class for create money in "Baht" currency of Thai.
 *
 * @author Pakanon Pantisawat
 */

public class ThaiMoneyFactory extends MoneyFactory {
    private static long nextSerialNumber = 100_000_000L;

    /**
     * Constructor call from its super class.
     */
    public ThaiMoneyFactory() {
        super();
    }

    /**
     * Create new money with valid value in "Baht" of Thai.
     *
     * @param value value of the money.
     * @return new money object of the money with valid value in "Baht".
     * @throws IllegalArgumentException if value is not a valid value of actual Thai Baht.
     */
    @Override
    public Valuable createMoney(double value) {
        double[] amount = {
                1D, 2D, 5D, 10D, 20D, 50D, 100D, 500D, 1000D
        };
        if (value < 0) throw new IllegalArgumentException("Value can't be less than 0.");
        for (double am : amount) {
            if (value == am) {
                if (value < 20) return new Coin(value, "Baht");
                else {
                    BankNote bankNote = new BankNote(value, "Baht");
                    bankNote.setSerialNumber(nextSerialNumber++);
                    return bankNote;
                }
            }
        }
        throw new IllegalArgumentException("Cannot create " + value + "Baht.");
    }
}
