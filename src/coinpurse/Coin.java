package coinpurse;

/**
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Pakanon Pantisawat
 */

public class Coin implements Comparable<Coin> {

    private double value;
    private String currency;

    /**
     * Constructor of Coin, initialize the value and the currency of the coin
     * @param value the value of coin must not be negative number
     * @param currency the currency of the coin
     */
    Coin(double value, String currency) {
        if (value < 0) throw new IllegalArgumentException("Coin value can't be negative amount");
        this.value = value;
        this.currency = currency;
    }


    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Check if two coins are equal in both value and currency.
     * @param arg another object to check
     * @return true if both value and currency of two coins are equal.
     */
    @Override
    public boolean equals(Object arg) {
        if (arg == this) return true;
        if (this.getClass() != arg.getClass()) return false;

        Coin aCoin = ((Coin) arg);

        return aCoin.getValue() == this.value && aCoin.getCurrency().equals(this.currency);
    }

    /**
     * Compare the value of this coin.
     * @param coin the coin to compare
     * @return -1 if this coin has order before another coin, 1 if this coin's order after the another coin and 0 if equals.
     */
    @Override
    public int compareTo(Coin coin) {
        if (coin.getValue() == this.value) return 0;
        else {
            return this.getValue() > coin.value ? 1 : -1;
        }
    }

    /**
     * Formatting string in more readable form.
     * @return string format of coin such as "5.00-Bath"
     */
    @Override
    public String toString() {
        return String.format("%.2f-%s", value, currency);
    }
}
