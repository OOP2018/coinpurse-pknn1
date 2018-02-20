package coinpurse;

/**
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Pakanon Pantisawat
 */

public class Coin extends Money {

    /**
     * Constructor of Coin, initialize the value and the currency of the coin
     * @param value the value of coin must not be negative number
     * @param currency the currency of the coin
     */
    public Coin(double value, String currency) {
        super(value, currency);
    }

    /**
     * Formatting string in more readable form.
     * @return string format of coin such as "5.00-Bath"
     */
    @Override
    public String toString() {
        if (this.getCurrency().equals("Ringgit")) {
            return String.format("%.0f-Sen", this.getValue() * 100);
        }
        return String.format("%.0f-%s", this.getValue(), this.getCurrency());
    }
}
