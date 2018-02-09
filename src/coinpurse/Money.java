package coinpurse;

public class Money implements Valuable {
    private double value;
    private String currency;

    public Money(double value, String currency) {
        this.value = value;
        this.currency = currency;
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
     * Check if two coins are equal in both value and currency.
     *
     * @param arg another object to check
     * @return true if both value and currency of two coins are equal.
     */
    @Override
    public boolean equals(Object arg) {
        if (arg == this) return true;

        if (arg == null || this.getClass() != arg.getClass()) return false;

        Money other = ((Money) arg);

        return other.getValue() == this.getValue() && other.getCurrency().equals(this.getCurrency());
    }

    /**
     * Compare two valuables by currency first. If both are equals, they will be compared by value.
     *
     * @param o Another valuable to compare with this valuable.
     * @return -1 if this is less, 0 if equals and 1 if this is greater than o.
     */
    @Override
    public int compareTo(Valuable o) {
        if (this.getCurrency().equals(o.getCurrency())) {
            return Double.compare(this.getValue(), o.getValue());
        }
        return this.getCurrency().compareTo(o.getCurrency());
    }
}
