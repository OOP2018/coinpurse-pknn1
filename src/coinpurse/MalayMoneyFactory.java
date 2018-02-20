package coinpurse;

/**
 * Money Factory class for create money in "Ringgit" currency of Malaysia.
 *
 * @author Pakanon Pantisawat
 */

public class MalayMoneyFactory extends MoneyFactory {
    private static long nextSerialNumber = 100_000_000L;

    /**
     * Constructor call from its super class.
     */
    public MalayMoneyFactory() {
        super();
    }

    /**
     * Create new Money with valid value in Ringgit.
     *
     * @param value value of the money.
     * @return Money object with value in Ringgit.
     * @throws IllegalArgumentException if the value is not the value of the actual Ringgit money.
     */
    @Override
    public Valuable createMoney(double value) {
        double[] amount = {
                0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100
        };
        if (value < 0) throw new IllegalArgumentException("Value cannot be less than 0.");
        for (double am : amount) {
            if (value == am) {
                if (value < 1) return new Coin(value, "Ringgit");
                else {
                    BankNote bankNote = new BankNote(value, "Ringgit");
                    bankNote.setSerialNumber(nextSerialNumber++);
                    return bankNote;
                }
            }
        }
        throw new IllegalArgumentException("Cannot create " + value + " Ringgit money.");
    }
}
